;;; Sierra Script 1.0 - (do not remove this comment)
(script# 371)
(include game.sh)
(use Main)
(use Invent)
(use System)

(public
	tahitiInv 0
)

(instance tahitiInv of Code

	(method (init)
		(inventory add: Black_Book Change Key Earring)
	)
	
	(method (dispose)
		(inventory delete: Black_Book Change Key Earring)
		(DisposeScript 371)
	)
)

(instance Black_Book of InvItem
	(properties
		said '/book[<black,address,call]'
		view {tahitiInv}
		loop 1
		value 1
		name "Black Book"
	)
)

(instance Change of InvItem
	(properties
		said '/change,coin'
		view {Black Book}
		loop 2
		value 1
	)
)

(instance Key of InvItem
	(properties
		said '/key'
		view {Black Book}
		loop 1
		value 1
	)
)

(instance Earring of InvItem
	(properties
		said '/earring'
		view 313
		value 1
	)
)
