;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INVENT.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Bob Heitman
;;;;
;;;;	Classes to manage inventory (items in the possession of other objects
;;;;	in an adventure game).
;;;;
;;;;	Classes:
;;;;		InvItem
;;;;		Inventory
;;;;
;;;;	Objects:
;;;;		invDialog


(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use System)

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
		view 0				;picture of the item
		loop 0
		cel 0
	)

;;;	(methods
;;;		saidMe				;does user input match the said spec?
;;;		ownedBy				;return TRUE if owned by given object
;;;		showSelf				;display this item
;;;		moveTo				;change ownership of this item
;;;		changeState
;;;	)



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

		(ShowView 
			(if description
				description
			else
				name
			)
			view loop cel
		)
	)
)


(class Inventory kindof Set
	;;; This is the set of all inventory items in the game.

	(properties
		name "Inv"
		carrying	"You are carrying:"
			;Title of the inventory display when the object in question
			;has some inventory items.
		empty "You are carrying nothing!"
			;Title of the inventory display when the object in question
			;has no inventory items.
	)

;;;	(methods
;;;		showSelf			;display inventory owned by an object
;;;		saidMe			;return InvItem matching user input
;;;		ownedBy			;return InvItem owned by an object
;;;	)


	(method (init)
		(= inventory self)
	)

	
	(method (showSelf whom &tmp temp0 [temp1 30] [str 301] node obj)
		(StrCpy @str carrying)
		(= temp0 0)
		(= node (inventory first:))
		(while node
			(if ((= obj (NodeValue node)) ownedBy: whom)
				(if temp0 (StrCat @str {,}))
				(++ temp0)
				(StrCat @str (Format @temp1 995 0 (obj name?)))
			)
			(= node (inventory next: node))
		)
		(if temp0
			(StrCat @str {.})
			(Print @str)
		else
			(Print empty)
		)
	)
	
	(method (saidMe)
		;; Return the ID of the first item in the inventory whose said
		;; spec matches user input.

		(return (self firstTrue: #saidMe:))
	)


	(method (ownedBy whom)
		;; Return the first item in inventory which is owned by 'whom'.

		(return (self firstTrue: #ownedBy: whom))
	)

)
