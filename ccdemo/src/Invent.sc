;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use System)

;NOTE: This script is tailor-made for this game.

(local
	yesI
)
; a stock list will be able to handle the scanning required to:
; find items by parsed name (firstTrue: #saidMe:)
; build status dialog (first, next)
; find items in room (firstTrue: #ownedBy)
; reference items by number (at: enumName)



(class InvItem kindof Object
	;;; An InvItem is something which can be owned by an object in an
	;;; adventure game.

	(properties
		-info- $8004		;(| CLASSBIT NODISPLAY)
		name "InvI"			;my literal name
		said 0				;said spec which user can type to identify this item
		description 0		;long text description
		owner 0				;who owns this item
;;;		view 0				;picture of the item
;;;		loop 0
;;;		cel 0
;;;		script 0				;a script that can control the item
		number 0
	)
	
	(method (saidMe)
		;; Return TRUE if my "said" was "parsed"

		(return (Said said))
	)


	(method (ownedBy id)
		;; Return TRUE if owned by this ID.

		(return (== owner id))
	)
	

	(method (moveTo id)
		;; Set my "owner" to passed ID.

		(= owner id)
		(return self)
	)

	(method (showSelf)
		;; Display this object.
		(Print description)
;;;		(ShowView 
;;;			(if description
;;;				description
;;;			else
;;;				name
;;;			)
;;;			view loop cel
;;;		)
	)
	

)

(class Inventory kindof Set
	;;; This is the set of all inventory items in the game.

	(properties
		name "Inv"
		carrying {You carry with you:\n}
		;Title of the inventory display when the object in question

		empty {You are carrying nothing!}
		;Title of the inventory display when the object in question
			;has no inventory items.

	)
	
	(method (init)
		(= inventory self)
	)
	
	(method (showSelf what &tmp i [invBuf 30] [descBuf 501] node temp533)
		(StrCpy @descBuf carrying)
		(= i 0)
		(= node (inventory first:))
		(while node
			(if
			((= temp533 (NodeValue node)) ownedBy: what)
				(if i (StrCat @descBuf {\n}))
				(++ i)
				(if (> (temp533 number?) 1)
					(StrCat
						@descBuf
						(Format @invBuf 995 0 (temp533 number?) (temp533 name?))
					)
				else
					(StrCat @descBuf (Format @invBuf 995 1 (temp533 name?)))
				)
			)
			(= node (inventory next: node))
		)
		(if i (Print @descBuf) else (Print empty))
	)
	
	(method (saidMe what)
		(self firstTrue: #saidMe what)
	)
	
	(method (ownedBy who)
		(self firstTrue: #ownedBy who)
	)
)
