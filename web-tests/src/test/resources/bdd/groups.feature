Feature: Groups

        Scenario Outline: Group creation
                Given a set of groups
                When I create a new group with name "<name>", header "<header>" and comment "<comment>"
                Then the new set of groups is equal to the old set with the added group

        Examples:
                | name | header | comment |
                | Tgro | TeSter | test123 |