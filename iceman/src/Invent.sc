;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use System)


(class InvItem of Object
	(properties
		name "InvI"
		said 0
		description 0
		owner 0
		view 0
		loop 0
		cel 0
		script 0
		value 0
	)
	
	(method (showSelf)
		(Print
			(+ gamePhase 612)
			(inventory indexOf: self)
			82
			view
			loop
			cel
		)
	)
	
	(method (saidMe)
		(Said said)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (moveTo theOwner)
		(if (and (== (= owner theOwner) ego) value)
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)
	
	(method (changeState newState)
		(if script (script changeState: newState))
	)
)

(class Inventory of Set
	(properties
		name "Inv"
		elements 0
		size 0
		carrying {You are carrying:}
		empty {You are carrying nothing!}
	)
	
	(method (init)
		(= inventory self)
	)
	
	(method (showSelf param1)
		((ScriptID 829 0) text: carrying doit: param1)
	)
	
	(method (saidMe)
		(self firstTrue: #saidMe)
	)
	
	(method (ownedBy param1)
		(self firstTrue: #ownedBy param1)
	)
)
