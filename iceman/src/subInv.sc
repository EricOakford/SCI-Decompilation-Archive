;;; Sierra Script 1.0 - (do not remove this comment)
(script# 372)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use Invent)
(use System)

(public
	subInv 0
)

(instance subInv of Code
	(properties)
	
	(method (init)
		(inventory
			add:
				Explosive
				Rum_Bottle
				Storage_Compartment_Key
				Diver
				Flare
				Device
				Cotter_pin
				Washer
				Nut
				Metal_Cylinder
				Vernier_Caliper
				Code_Book
				Hammer
				Open_End_Wrench
				sheared_cylinder
		)
	)
	
	(method (dispose)
		(inventory
			delete:
				Explosive
				Rum_Bottle
				Storage_Compartment_Key
				Diver
				Flare
				Device
				Cotter_pin
				Washer
				Nut
				Metal_Cylinder
				Vernier_Caliper
				Code_Book
				Hammer
				Open_End_Wrench
				sheared_cylinder
		)
		(DisposeScript 372)
	)
)

(instance Explosive of InvItem
	(properties
		said '/bomb'
		view 330
		value 1
	)
)

(instance Rum_Bottle of InvItem
	(properties
		said '/bottle[<rum]'
		view 330
		cel 1
		value 1
		name "Rum Bottle"
	)
)

(instance Storage_Compartment_Key of InvItem
	(properties
		said '/key'
		view 330
		cel 2
		value 1
		name "Storage Compartment Key"
	)
)

(instance Diver of InvItem
	(properties
		said '/diver'
		view 337
		loop 1
		value 1
	)
)

(instance Flare of InvItem
	(properties
		said '/flare'
		view 330
		cel 3
		value 1
	)
)

(instance Device of InvItem
	(properties
		said '/device'
		view 339
		value 10
	)
)

(instance Cotter_pin of InvItem
	(properties
		said '/pin[<cotter]'
		view 337
		loop 2
		value 1
		name "Cotter pin"
	)
)

(instance Washer of InvItem
	(properties
		said '/washer'
		view 337
		loop 1
		cel 3
		value 1
	)
	
	(method (showSelf)
		(switch (subMarine invStatus4?)
			(1
				(Print 372 0 #icon view loop cel)
			)
			(2
				(Print 372 1 #icon view loop cel)
			)
			(3
				(Print 372 2 #icon view loop cel)
			)
			(4
				(Print 372 3 #icon view loop cel)
			)
		)
	)
)

(instance Nut of InvItem
	(properties
		said '/nut'
		view 337
		loop 1
		cel 2
		value 1
	)
	
	(method (showSelf)
		(switch (subMarine invStatus3?)
			(1
				(Print 372 4 #icon view loop cel)
			)
			(2
				(Print 372 5 #icon view loop cel)
			)
			(3
				(Print 372 6 #icon view loop cel)
			)
			(4
				(Print 372 7 #icon view loop cel)
			)
		)
	)
)

(instance Metal_Cylinder of InvItem
	(properties
		said '/cylinder[<metal]'
		view 337
		loop 1
		cel 1
		value 1
		name "Metal Cylinder"
	)
	
	(method (showSelf)
		(switch (subMarine invStatus1?)
			(1
				(Print 372 8 #icon view loop cel)
			)
			(2
				(Print 372 9 #icon view loop cel)
			)
			(3
				(Print 372 10 #icon view loop cel)
			)
			(4
				(Print 372 11 #icon view loop cel)
			)
		)
	)
)

(instance Vernier_Caliper of InvItem
	(properties
		said '/caliper'
		view 337
		cel 3
		value 1
		name "Vernier Caliper"
	)
)

(instance Code_Book of InvItem
	(properties
		said '/book[<code]'
		view 431
		value 3
		name "Code Book"
	)
)

(instance Hammer of InvItem
	(properties
		said '/hammer'
		view 337
		value 1
	)
)

(instance Open_End_Wrench of InvItem
	(properties
		said '/wrench'
		view 337
		cel 2
		value 1
		name "Open End Wrench"
	)
	
	(method (showSelf)
		(switch (subMarine invStatus2?)
			(1
				(Print 372 12 #icon view loop cel)
			)
			(2
				(Print 372 13 #icon view loop cel)
			)
			(3
				(Print 372 14 #icon view loop cel)
			)
			(4
				(Print 372 15 #icon view loop cel)
			)
		)
	)
)

(instance sheared_cylinder of InvItem
	(properties
		said '/cylinder<sheared'
		view 337
		loop 1
		cel 1
		value 1
		name "sheared cylinder"
	)
)
