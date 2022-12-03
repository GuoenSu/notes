# flights tickets

## commission: include frontend and backend

```java
return new AggregateResult(frontend, backend, forceIt, endorsement);
```

- **frontend**: from the **contract** table, compare to PnrRetrieveResponse payload, get the matched commissionRules, and get the matched commission by fareBasis or bookingClass, then calculate base on `routing.getPrice().basePrice()`

```java
    contract.getCommissionRules();

        List<DtoCommission> selected = getSegments().stream()
                .map(segment -> matchedCommission(commissionRule.getCommissions(), segment))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());    

        // default commission
        if (CollectionUtils.isEmpty(selected)) return Optional.of(commissionRule.getDefaultCommission());

        // contract.mixed = Mixed.HIGHER
        if (higher) return selected.stream().max(Comparator.comparing(DtoCommission::getAmount));

        // contract.mixed = Mixed.LOWER
        return selected.stream().min(Comparator.comparing(DtoCommission::getAmount));

        commission.calculate(routing.getPrice().basePrice()).setScale(2, BigDecimal.ROUND_DOWN);
```

- **backend** = basePrice (befor tax) - frontend, backend commission from the backend table

```java
BigDecimal backendCommission = dtoCommission.calculate(payload.getRouting().getPrice().basePrice().subtract(frontend));
```

## contract

## Auto Issue Tickets

## QC