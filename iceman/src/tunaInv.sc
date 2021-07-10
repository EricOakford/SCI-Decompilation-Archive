;;; Sierra Script 1.0 - (do not remove this comment)
(script# 374)
(include game.sh)
(use Main)
(use Intrface)
(use Invent)
(use System)

(public
	tunaInv 0
)

(instance tunaInv of Code
	
	(method (init)
		(inventory
			add:
				Fish
				Capsule
				Map
				Key
				Duct_Tape
				Tranquilizer_Gun
				Sugar_Canister
				Flour_Canister
				Coffee_Canister
				Business_Card
				Food_Platter
				Note
		)
	)
	
	(method (dispose)
		(inventory
			delete:
				Fish
				Capsule
				Map
				Key
				Duct_Tape
				Tranquilizer_Gun
				Sugar_Canister
				Flour_Canister
				Coffee_Canister
				Business_Card
				Food_Platter
				Note
		)
		(DisposeScript 374)
	)
)

(instance Fish of InvItem
	(properties
		said '/fish'
		view 372
		cel 1
		value 1
	)
)

(instance Capsule of InvItem
	(properties
		said '/capsule'
		view 372
		cel 2
		value 1
	)
)

(instance Map of InvItem
	(properties
		said '/map'
		view 372
		cel 3
		value 1
	)
	
	(method (showSelf)
		(if loop
			(Print 374 0 #icon view loop cel)
		else
			(Print 374 1 #icon view loop cel)
		)
	)
)

(instance Key of InvItem
	(properties
		said '/key'
		view 372
		loop 1
		cel 1
		value 1
	)
)

(instance Duct_Tape of InvItem
	(properties
		said '/tape'
		view 372
		loop 1
		cel 2
		value 1
		name "Duct Tape"
	)
)

(instance Tranquilizer_Gun of InvItem
	(properties
		said '/gun'
		view 384
		loop 3
		value 1
		name "Tranquilizer Gun"
	)
)

(instance Sugar_Canister of InvItem
	(properties
		said '/canister<sugar'
		view 384
		value 1
		name "Sugar Canister"
	)
)

(instance Flour_Canister of InvItem
	(properties
		said '/canister<flour'
		view 384
		cel 1
		value 1
		name "Flour Canister"
	)
)

(instance Coffee_Canister of InvItem
	(properties
		said '/canister<coffee'
		view 384
		cel 2
		value 1
		name "Coffee Canister"
	)
)

(instance Business_Card of InvItem
	(properties
		said '/card'
		view 384
		loop 1
		cel 1
		value 1
		name "Business Card"
	)
)

(instance Food_Platter of InvItem
	(properties
		said '/platter'
		view 384
		loop 1
		value 1
		name "Food Platter"
	)
)

(instance Note of InvItem
	(properties
		said '/note'
		view 384
		loop 4
		cel 1
		value 1
	)
)
