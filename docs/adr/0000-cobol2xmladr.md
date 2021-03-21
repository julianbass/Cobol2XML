# Cobol2XMLADR

* Status: proposed
* Date: 2021-03-21

## Context and Problem Statement

Can I extract Cobol language statements into XML?

## Decision Drivers

* maintainability

## Considered Options

* Use pipes and filters pattern for parser

## Decision Outcome

Chosen option: "", because comes out best.

## Pros and Cons of the Options

### Use pipes and filters pattern for parser

* Good, because Each stage can be maintained separately
* Bad, because Almost all the parsing is actually done on one stage
