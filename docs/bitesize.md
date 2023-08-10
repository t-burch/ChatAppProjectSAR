# ChatAppProjectSAR

## Anforderungen:
1. Chat App (w/ Frontend)
2. Request/Response Pattern zwischen Clients
3. Nur Nachrichten der App sollen akzeptiert werden
4. Es soll eine Schnittstelle für zukünftige Nachrichten existieren
5. Unit Tests/Integration Tests
6. Multithreading soll implementiert sein

## Kategorisierung:
1. Kommunikations-Engine
    - Hash-basierter Datenaustausch (Request Response)
    - Discover
        - Discover Token?
        - B/P2P (Broadcast-Peer-2-Peer)
2. Multithreading/Asynchron
3. Frontend über Custom Terminal Renderer


Event Driven!

## Stack:
- UPnP Networking (& JWT?)
- Protocol Buffers
- Java Maven Kotlin
- JUnit5 MockK
- xxHash