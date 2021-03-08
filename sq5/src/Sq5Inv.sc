;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ5INV)
(include game.sh) (include "15.shm")
(use Main)
(use ScrollableInventory)
(use ScrollInsetWindow)
(use Print)
(use IconBar)
(use Invent)
(use System)

(public
	invCode 0
	invWin 1
)

(class Sq5InvItem of InvItem
	(properties
		modNum 0
	)
	
	(method (doVerb theVerb &tmp thePort theWidth)
		(if (not modNum)
			(= modNum curRoomNum)
		)
		(switch theVerb
			(V_LOOK
				(if (Message MsgSize modNum noun V_LOOK NULL 1)
					(= theWidth (CelWide view loop cel))
					(= thePort (GetPort))
					(Print
						addIcon: view loop cel 0 0
						addText: noun V_LOOK NULL 1 (+ theWidth 4) 0 modNum
						init:
					)
					(SetPort thePort)
				)
			)
			(V_DO
				(if (Message MsgSize modNum noun V_DO NULL 1)
					(= thePort (GetPort))
					(Print addText: noun V_DO NULL 0 0 0 modNum init:)
					(SetPort thePort)
				else
					(= thePort (GetPort))
					(Print addText: NULL V_DO NULL 0 0 0 modNum init:)
					(SetPort thePort)
				)
			)
			(else 
				(if (Message MsgSize modNum noun theVerb NULL 1)
					(= thePort (GetPort))
					(Print addText: noun theVerb NULL 0 0 0 modNum init:)
					(SetPort thePort)
				else
					(= thePort (GetPort))
					(Print addText: NULL V_USEIT NULL 0 0 0 modNum init:)
					(SetPort thePort)
				)
			)
		)
	)
)

(instance sq5Inv of ScrollableInventory
	
	(method (init)
		(super init: &rest)
		(invWin
			color: 0
			back: 3
			topBordColor: 5
			lftBordColor: 4
			rgtBordColor: 2
			botBordColor: 1
			insideColor: 2
			topBordColor2: 1
			lftBordColor2: 1
			botBordColor2: 6
			rgtBordColor2: 6
		)
		(self
			add:
				Buckazoids
				Floor_Scrubber
				Safety_Cones
				Distributor_Cap
				Transporter_Fuse
				Antacid
				Ship_Opener
				Cutting_Torch
				Spike
				Oxygen_Tank
				Communicator
				Hole_Punch
				Cloaking_Device
				Space_Monkeys_Package
				Business_Card
				Genetix_Canister
				Liquid_Nitro_Tank
				Kiz_Branch
				Kiz_Fruit
				frock
				WD40_Head
				Oxygen_Mask
				Paper
			eachElementDo: #lowlightColor 2
			add: invLook invSelect invHelp invUp invDown ok
		)
		(self
			state: NOCLICKHELP
			upIcon: invUp
			downIcon: invDown
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			numCols: 5
			scrollAmount: 5
			dispAmount: 10
			empty: N_EMPTY
			normalHeading: 15
			eachElementDo: #highlightColor 0
			eachElementDo: #modNum 15
			eachElementDo: #init
		)
	)
)

(instance invCode of Code
	
	(method (init)
		((= inventory sq5Inv) init:)
	)
)

(instance invWin of ScrollInsetWindow
	(properties
		priority -1
		topBordHgt 30
		botBordHgt 5
	)
	
	(method (open)
		(invLook
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 84)
		)
		(invLook nsTop: 2)
		(super open: &rest)
	)
)

(instance invUp of IconItem
	(properties
		view 991
		loop 5
		cel 0
		cursor ARROW_CURSOR
		maskView 991
		maskLoop 5
		maskCel 2
		lowlightColor 5
		noun N_SCROLL_UP
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
		view 991
		loop 6
		cel 0
		cursor ARROW_CURSOR
		maskView 991
		maskLoop 6
		maskCel 2
		lowlightColor 5
		noun N_SCROLL_DOWN
		helpVerb V_HELP
	)
	
	(method (select)
		(if (super select: &rest)
			(inventory scroll: 1)
		)
		(return FALSE)
	)
)

(instance ok of IconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		lowlightColor 5
		noun N_OK
		helpVerb 5
	)
)

(instance invLook of IconItem
	(properties
		view 991
		loop 2
		cel 0
		cursor 981
		message V_LOOK
		signal(| FIXED_POSN RELVERIFY)
		lowlightColor 5
		noun N_LOOK
		helpVerb V_HELP
	)
)

(instance invHand of IconItem	;this was used in the beta, but is unused in the final version
	(properties
		view 991
		loop 0
		cel 0
		cursor 982
		message V_DO
		lowlightColor 5
		noun N_DO
		helpVerb V_HELP
	)
)

(instance invHelp of IconItem
	(properties
		view 991
		loop 1
		cel 0
		cursor 989
		message V_HELP
		lowlightColor 5
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance invSelect of IconItem
	(properties
		view 991
		loop 4
		cel 0
		cursor ARROW_CURSOR
		lowlightColor 5
		noun N_SELECT
		helpVerb V_HELP
	)
)

(instance Buckazoids of Sq5InvItem
	(properties
		view 900
		loop 1
		cursor 900
		message V_BUCKAZOIDS
		signal IMMEDIATE
		noun N_BUCKAZOIDS
	)
)

(instance Floor_Scrubber of Sq5InvItem
	(properties
		view 901
		loop 1
		cursor 901
		message V_SCRUBBER
		signal IMMEDIATE
		noun N_SCRUBBER
		name "Floor Scrubber"
	)
)

(instance Safety_Cones of Sq5InvItem
	(properties
		view 903
		loop 1
		cursor 903
		message V_SAFETY_CONES
		signal IMMEDIATE
		noun N_SAFETY_CONES
		name "Safety Cones"
	)
)

(instance Distributor_Cap of Sq5InvItem
	(properties
		view 904
		loop 1
		cursor 904
		message V_DIST_CAP
		signal IMMEDIATE
		noun N_DIST_CAP
		name "Distributor Cap"
	)
)

(instance Transporter_Fuse of Sq5InvItem
	(properties
		view 905
		loop 1
		cursor 905
		message V_FUSE
		signal IMMEDIATE
		noun N_FUSE
		name "Transporter Fuse"
	)
)

(instance Antacid of Sq5InvItem
	(properties
		view 906
		loop 1
		cursor 906
		message V_ANTACID
		signal IMMEDIATE
		noun N_ANTACID
	)
)

(instance Ship_Opener of Sq5InvItem
	(properties
		view 907
		loop 1
		cursor 907
		message V_SHIP_OPENER
		signal IMMEDIATE
		noun N_SHIP_OPENER
		name "Ship Opener"
	)
)

(instance Cutting_Torch of Sq5InvItem
	(properties
		view 908
		loop 1
		cursor 908
		message V_TORCH
		signal IMMEDIATE
		noun N_TORCH
		name "Cutting Torch"
	)
)

(instance Spike of Sq5InvItem
	(properties
		view 909
		loop 1
		cursor 909
		message V_SPIKE
		signal IMMEDIATE
		noun N_SPIKE
	)
)

(instance Oxygen_Tank of Sq5InvItem
	(properties
		view 917
		loop 1
		cursor 917
		message V_OXYGEN_TANK
		signal IMMEDIATE
		noun N_OXYGEN_TANK
		name "Oxygen Tank"
	)
)

(instance Communicator of Sq5InvItem
	(properties
		view 911
		loop 1
		cursor 911
		message V_COMMUNICATOR
		signal IMMEDIATE
		noun N_COMMUNICATOR
	)
)

(instance Hole_Punch of Sq5InvItem
	(properties
		view 912
		loop 1
		cursor 912
		message V_HOLE_PUNCH
		signal IMMEDIATE
		noun N_HOLE_PUNCH
		name "Hole Punch"
	)
)

(instance Cloaking_Device of Sq5InvItem
	(properties
		view 913
		loop 1
		cursor 913
		message V_CLOAKING_DEVICE
		signal IMMEDIATE
		noun N_CLOAKING_DEVICE
		name "Cloaking Device"
	)
)

(instance Space_Monkeys_Package of Sq5InvItem
	(properties
		view 914
		loop 1
		cursor 914
		message V_SPACE_MONKEYS
		signal IMMEDIATE
		noun N_SPACE_MONKEYS
		name "Space Monkeys Package"
	)
	
	(method (doVerb theVerb &tmp thePort theWidth)
		(if (not modNum)
			(= modNum curRoomNum)
		)
		(if (Message MsgSize modNum noun theVerb 0 1)
			(= theWidth (CelWide 529 0 0))
			(= thePort (GetPort))
			(Print
				addIcon: 529 0 0 0 0
				addText: noun theVerb 0 1 (+ theWidth 4) 0 modNum
				init:
			)
			(SetPort thePort)
		)
	)
)

(instance Business_Card of Sq5InvItem
	(properties
		view 915
		loop 1
		cursor 915
		message V_BUSINES_CARD
		signal IMMEDIATE
		noun N_BUSINESS_CARD
		name "Business Card"
	)
	
	(method (doVerb theVerb &tmp thePort)
		(if (== theVerb V_HOLE_PUNCH)
			(if (OneOf curRoomNum 201 200 228 226 620 640)
				(= thePort (GetPort))
				(Print addText: noun theVerb C_CANT_DO 1 0 0 SQ5INV init:)
				(SetPort thePort)
			else
				(inventory hide:)
				((ScriptID 2000 0) doit:)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance Genetix_Canister of Sq5InvItem
	(properties
		view 916
		loop 1
		cursor 916
		message V_GENETIX_CAN
		signal IMMEDIATE
		noun N_GENETIX_CAN
		name "Genetix Canister"
	)
)

(instance Liquid_Nitro_Tank of Sq5InvItem
	(properties
		view 910
		loop 1
		cursor 910
		message V_NITRO_TANK
		signal IMMEDIATE
		noun N_NITRO_TANK
		name "Liquid Nitro Tank"
	)
)

(instance Kiz_Branch of Sq5InvItem
	(properties
		view 918
		loop 1
		cursor 918
		message V_KIZ_BRANCH
		signal IMMEDIATE
		noun N_KIZ_BRANCH
		name "Kiz Branch"
	)
)

(instance Kiz_Fruit of Sq5InvItem
	(properties
		view 919
		loop 1
		cursor 919
		message V_KIZ_FRUIT
		signal IMMEDIATE
		noun N_KIZ_FRUIT
		name "Kiz Fruit"
	)
)

(instance frock of Sq5InvItem
	(properties
		view 920
		loop 1
		cursor 920
		message V_FROC
		signal IMMEDIATE
		noun N_FROC
	)
)

(instance WD40_Head of Sq5InvItem
	(properties
		view 921
		loop 1
		cursor 921
		message V_WD40_HEAD
		signal IMMEDIATE
		noun N_WD40_HEAD
		name "WD40 Head"
	)
)

(instance Oxygen_Mask of Sq5InvItem
	(properties
		view 922
		loop 1
		cursor 922
		message V_REBREATHER
		signal IMMEDIATE
		noun N_REBREATHER
		name "Oxygen Mask"
	)
)

(instance Paper of Sq5InvItem
	(properties
		view 923
		loop 1
		cursor 923
		message V_PAPER
		signal IMMEDIATE
		noun N_PAPER
	)
	
	(method (doVerb theVerb &tmp thePort theWidth)
		(if (not modNum) (= modNum curRoomNum))
		(if (Message MsgSize modNum noun theVerb 0 1)
			(= theWidth (CelWide 614 0 0))
			(= thePort (GetPort))
			(Print
				addIcon: 614 0 0 0 0
				addText: noun theVerb NULL 1 (+ theWidth 4) 0 modNum
				init:
			)
			(SetPort thePort)
		)
	)
)
