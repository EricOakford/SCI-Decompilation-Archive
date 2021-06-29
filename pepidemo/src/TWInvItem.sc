;;; Sierra Script 1.0 - (do not remove this comment)
(script# TWINV)
(include game.sh) (include "894.shm")
(use Main)
(use ScrollableInventory)
(use TwistyInsetWindow)
(use IconBar)
(use LoadMany)
(use Invent)
(use User)
(use System)

(public
	invCode 0
	invWin 1
	inventoryCursor 2
)

(class TWInvItem of InvItem
	(properties
		view 907
	)
	
	(method (init)
		(self
			highlightColor: myHighlightColor
			lowlightColor: 5
			cursor: inventoryCursor
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp [str 20])
		(cond 
			(
				(and
					(!= modNum TWINV)
					(Message MsgGet modNum noun theVerb NULL 1 @str)
				)
				(messager say: noun theVerb NULL 1 0 modNum)
			)
			(
				(and
					(!= modNum TWINV)
					(Message MsgGet modNum noun NULL NULL 1 @str)
				)
				(messager say: noun NULL NULL 1 modNum)
			)
			((Message MsgGet TWINV noun theVerb NULL 1 @str)
				(messager say: noun theVerb NULL 1 0 TWINV)
			)
			((Message MsgGet TWINV noun NULL NULL 1 @str)
				(messager say: noun NULL NULL 1 0 TWINV)
			)
			(else
				(doVerbCode doit: theVerb self)
			)
		)
	)
	
	(method (cue)
		(inventory showSelf: ego)
	)
)

(instance inventoryCursor of Cursor
	(properties
		view 909
	)
	
	(method (init)
		(if
			(and
				(IsObject (inventory curIcon?))
				((inventory curIcon?) isKindOf: TWInvItem)
			)
			(self
				cel: ((inventory curIcon?) cel?)
				loop: ((inventory curIcon?) loop?)
			)
		)
		(super init: &rest)
	)
)

(instance twistyInv of ScrollableInventory
	
	(method (init)
		(invWin
			color: 5
			back: 59
			topBordColor: myTopBordColor
			lftBordColor: myLowlightColor
			rgtBordColor: myLftBordColor
			botBordColor: myBotBordColor
			insideColor: 5
			topBordColor2: myBotBordColor
			lftBordColor2: myBotBordColor
			botBordColor2: myTopBordColor
			rgtBordColor2: myRgtBordColor
			botBordHgt: 25
		)
		(self
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(self
			add:
				Dog_Harness
				Herb_Cluster
				Herb_Packet
				Baby_Swaddling
				Pebbles
				Fleas
				invLook
				invHand
				invSelect
				invHelp
				invUp
				invDown
				ok
		)
		(self
			eachElementDo: #modNum TWINV
			eachElementDo: #init
			state: NOCLICKHELP
			upIcon: invUp
			downIcon: invDown
		)
	)
	
	(method (hide)
		((User curEvent?) claimed: TRUE)
		(super hide: &rest)
	)
)

(instance invCode of Code
	
	(method (init)
		((= inventory twistyInv) init:)
	)
)

(instance invWin of TwistyInsetWindow
	(properties
		priority -1
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open)
		(if (== ego (ScriptID TWEGO 0))
			(invLook loop: 2 cursor: 995)
			(invHand loop: 0 cursor: 992)
		else
			(invLook loop: 8 cursor: 998)
			(invHand loop: 7 cursor: 991)
		)
		(invLook
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 98)
		)
		(LoadMany RES_VIEW 901 900 902 907)
		(super open: &rest)
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		lowlightColor 36
		noun N_OK
		helpVerb V_HELP
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 995
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		lowlightColor 36
		noun N_EYES
		helpVerb V_HELP
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 992
		message V_DO
		lowlightColor 36
		noun N_HAND
		helpVerb V_HELP
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 990
		message V_HELP
		lowlightColor 36
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor ARROW_CURSOR
		lowlightColor 36
		noun N_SELECT
		helpVerb V_HELP
	)
)

(instance invUp of IconItem
	(properties
		view 901
		loop 5
		cel 0
		cursor ARROW_CURSOR
		maskView 901
		maskLoop 5
		maskCel 2
		lowlightColor 36
		noun N_UP
		modNum TWINV
		helpVerb V_HELP
	)
	
	(method (select)
		(if (super select: &rest)
			(inventory scroll: -1)
		)
		(return FALSE)
	)
)

(instance invDown of IconItem
	(properties
		view 901
		loop 6
		cel 0
		cursor ARROW_CURSOR
		maskView 901
		maskLoop 6
		maskCel 2
		lowlightColor 36
		noun N_DOWN
		modNum TWINV
		helpVerb V_HELP
	)
	
	(method (select)
		(if (super select: &rest)
			(inventory scroll: 1)
		)
		(return FALSE)
	)
)

(instance Dog_Harness of TWInvItem
	(properties
		cel 6
		message V_DOG_HARNESS
		signal IMMEDIATE
		noun N_DOG_HARNESS
		name "Dog Harness"
	)
)

(instance Herb_Cluster of TWInvItem
	(properties
		loop 1
		cel 5
		message V_HERB_CLUSTER
		signal IMMEDIATE
		noun N_HERB_CLUSTER
		name "Herb Cluster"
	)
)

(instance Herb_Packet of TWInvItem
	(properties
		loop 1
		cel 6
		message V_HERB_PACKET
		signal IMMEDIATE
		noun N_HERB_PACKET
		name "Herb Packet"
	)
)

(instance Baby_Swaddling of TWInvItem
	(properties
		loop 1
		cel 9
		signal IMMEDIATE
		name "Baby Swaddling"
	)
)

(instance Pebbles of TWInvItem
	(properties
		loop 2
		message V_PEBBLES
		signal IMMEDIATE
		noun N_PEBBLES
	)
)

(instance Fleas of TWInvItem
	(properties
		loop 2
		cel 4
		message V_FLEAS
		signal IMMEDIATE
		noun N_FLEAS
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== ego (ScriptID TWEGO 0))
					(messager say: N_FLEAS V_DO NULL 0 0 TWINV)
				else
					(messager say: N_FLEAS V_PAW NULL 0 0 TWINV)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
