# Queue/Ticketing System

## Overview

A Spring Boot-based queue management system designed for small waiting rooms. The system manages two types of service queues (Advisory and Cashier) with real-time updates across multiple interfaces. Customers use a kiosk to take tickets, operators manage their respective queues, and a hall display shows current ticket information on a TV screen.

## User Preferences

Preferred communication style: Simple, everyday language.

## System Architecture

### Backend Architecture
- **Framework**: Spring Boot with MVC pattern
- **Template Engine**: Thymeleaf for server-side rendering
- **Real-time Communication**: WebSocket using STOMP protocol over SockJS
- **Data Storage**: In-memory storage (no database) with automatic daily reset at midnight
- **Scheduling**: Spring's @Scheduled annotation for automated counter resets

### Frontend Architecture
- **View Layer**: Thymeleaf templates with responsive CSS
- **JavaScript**: Vanilla JavaScript for client-side interactions
- **Real-time Updates**: STOMP client for WebSocket connections
- **User Interfaces**:
  - Kiosk interface (`/kiosk`) - Large buttons for ticket generation
  - Operator panel (`/operator`) - Queue management with role-based access
  - Hall display (`/hall`) - TV-optimized real-time ticket display

### Queue Management System
- **Two Queue Types**: Advisory (A) and Cashier (C)
- **Ticket Format**: Prefix + 3-digit counter (A-001, C-001, etc.)
- **Role-based Access**: Operators can only manage their assigned queue type
- **Race Condition Prevention**: Button disabling during API calls

### API Design
- **RESTful Endpoints**:
  - `POST /api/tickets/{queue}` - Generate new ticket
  - `POST /api/tickets/{queue}/next` - Advance to next ticket
  - `GET /api/state` - Get current system state
- **Real-time Topics**: Queue-scoped WebSocket topics (`/topic/queue/A`, `/topic/queue/C`)

### State Management
- **In-Memory Storage**: Counters and queue state maintained in application memory
- **Daily Reset**: Automated midnight reset using server timezone
- **No Persistence**: Stateless design suitable for single-day operations

## External Dependencies

### WebSocket Libraries
- **SockJS**: WebSocket fallback and transport protocol
- **STOMP**: Messaging protocol for WebSocket communication

### Frontend Libraries
- **SockJS Client**: Browser-side WebSocket implementation
- **STOMP.js**: JavaScript STOMP client for real-time messaging

### Spring Boot Dependencies
- **Spring Web**: MVC framework and REST API support
- **Spring WebSocket**: Real-time communication infrastructure
- **Thymeleaf**: Template engine for server-side rendering
- **Spring Boot Starter**: Core Spring Boot functionality and auto-configuration