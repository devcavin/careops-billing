# CareOps Billing Platform

Financial and client management for care service providers — daycare first.

CareOps is the billing, client-relationship, and financial-visibility layer that daycare and other care service operators need but that generic school management systems handle poorly or not at all. It is deliberately **not** a daycare ERP — no classroom management, no curriculum, no attendance subsystem, no scheduling engine. It is a focused, composable subsystem that can sit alongside an operator's existing tools and own one thing well: accounts, dependents, enrollments, invoicing, payments, and receipts.

```
Existing Daycare/School System
          │
          │ integration (future)
          ▼
    CareOps Billing Platform
          │
          ├── Accounts / Guardians
          ├── Dependents
          ├── Services
          ├── Groups
          ├── Billing
          ├── Payments
          └── Financial Visibility
```

## Why this exists

Most school/daycare software treats billing as an afterthought bolted onto attendance and classroom tools. CareOps starts from the opposite direction: get the financial model right first — correct, auditable, and impossible to silently corrupt — then build the minimum client-relationship layer needed to make that financial model usable by real families and staff.

The core design commitment: **outstanding balance is always computed, never stored.** Charges and confirmed payments are immutable. Every partial payment, overpayment, and multi-invoice payment is represented explicitly through a `payment_allocations` table rather than a mutable balance field anywhere in the system.

## What it handles

- **Accounts, Guardians, and Dependents** — modeled as three distinct concepts, never conflated. An Account is the billing unit; Guardians have portal access; Dependents are the children receiving care.
- **Services and pricing history** — billing always uses the price that was active when the billing period occurred, not the current price.
- **Enrollment → Charge → Invoice** — a Dependent's enrollment in a Service generates immutable Charges, consolidated per Account per billing period into an Invoice.
- **Payment → Allocation → Receipt** — payments are recorded, allocated against specific invoice line items, and receipts are generated as immutable point-in-time snapshots.
- **Refunds and Credits** — modeled in the schema from day one; workflow logic is a later phase (see Roadmap).
- **Guardian portal** — account, dependents, services, invoices, balances, payment history, and receipts. No academic content, no classroom feed, no grades.
- **Relationship-derived authorization** — a Staff member's access to a Dependent or Account is derived from their Group assignment, not their role name alone, and is enforced at the query level.

## Stack

| Layer | Choice |
|---|---|
| Backend | Java, Spring Boot, Spring Security, Spring Data JPA |
| Auth | Spring Security + JWT (separate token paths for Staff and Guardians) |
| Database | PostgreSQL, Flyway migrations |
| Frontend | React, TypeScript, Vite, Tailwind CSS, React Query |
| DevOps | Docker, Docker Compose, GitHub Actions |
| Deployment | Render (backend), Vercel (frontend) |
| Architecture | Modular monolith — no microservices, no message broker |

Keycloak and OPA are deliberately out of MVP scope. Authorization here is a relationship-derived database query problem, not a policy-engine or identity-issuance problem — see the architecture notes for the full reasoning.

## Status

Actively designed project demonstrating multi-entity domain modeling, financial state correctness, payment provider abstraction, and resource-level authorization in Java/Spring

**First implementation milestone:** Account creation with at least one Guardian and one Dependent, enrolled in a Service, with an Invoice generated, a Payment recorded and allocated against it, producing a Receipt, visible in the Guardian portal.

## Roadmap

- **Phase 1 (current):** Core operations, billing, guardian portal
- **Phase 2:** External payment webhooks, refund/credit workflow, communication layer (email/SMS), OPA policy evaluation
- **Phase 3:** Accounting integrations, external daycare system integrations, advanced financial workflows

## Out of scope, permanently

Classroom management, curriculum, examinations/grades, attendance as a subsystem, lesson planning, transportation, cafeteria/hostel, payroll/HR, general ledger/accounting ERP, tax management, scheduling engine, marketplace, CRM. If a feature request pulls this toward school-ERP territory, it doesn't belong here.
