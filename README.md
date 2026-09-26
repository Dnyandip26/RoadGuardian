# 🚗 RoadGuardian

## AI-Powered Roadside Assistance & Vehicle Service Management System

RoadGuardian is a JavaFX-based smart roadside assistance and vehicle service management system designed to connect vehicle owners, mechanics, and administrators through a centralized digital platform.

The system provides roadside assistance, mechanic discovery, service requests, AI-based vehicle diagnosis, AI-powered repair cost estimation, emergency SOS assistance, tow truck requests, vehicle management, service history, notifications, reviews, complaints, and administrative management.


---

# 📖 About the Project

Vehicle breakdowns and unexpected repair problems can happen at any time. During such situations, vehicle owners may face difficulties finding a reliable mechanic, understanding the vehicle problem, estimating repair costs, or getting immediate roadside assistance.

RoadGuardian provides a centralized solution for these problems.

The platform connects customers with mechanics and administrators while providing AI-assisted vehicle diagnosis and repair cost estimation.

### Core Concept

```text
                    ┌──────────────────┐
                    │     CUSTOMER     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │   ROADGUARDIAN   │
                    └────────┬─────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
   │ AI Diagnosis│    │  Mechanic   │    │ Emergency   │
   │ & Cost      │    │ Assistance  │    │ Assistance │
   │ Estimation  │    │             │    │ / SOS / Tow│
   └─────────────┘    └─────────────┘    └─────────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     FIREBASE     │
                    │    FIRESTORE     │
                    └──────────────────┘
