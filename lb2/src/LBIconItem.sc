;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBINV)
(include game.sh) (include "15.shm")
(use Main)
(use BordWind)
(use IconBar)
(use Osc)
(use ForCount)
(use Timer)
(use Sound)
(use Invent)
(use Actor)
(use System)

(public
	invCode 0
	invWin 1
)

(local
	[str 20]
)
(class LBIconItem of IconItem
	
	(method (ownedBy)
		(return FALSE)
	)
)

(class LBInvItem of InvItem
	
	(method (init)
		(= lowlightColor myInsideColor)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(Message MsgGet LBINV noun V_TITLE NULL 1 @str)
		(narrator name: @str showTitle: TRUE)
		(cond 
			((and (> argc 1) theItem)
				(messager say: noun theVerb NULL 0 0 LBINV)
			)
			((== theVerb V_LOOK)
				(messager say: noun V_LOOK NULL 0 0 LBINV)
			)
			((== theVerb V_MAGNIFIER)
				(messager say: noun V_MAGNIFIER NULL 0 0 LBINV)
			)
			((not (OneOf theVerb V_WALK V_DO V_TALK V_ASK V_HELP V_EXIT))
				(messager say: NULL V_PRAGFAIL NULL 0 0 15)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invCode of Code
	
	(method (init)
		(= inventory Inventory)
		(Inventory
			init:
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			add:
				coupon
				claimTicket
				notebook
				sandwich
				baseball
				deskKey
				pressPass
				pocketWatch
				skeletonKey
				meat
				wireCutters
				daggerOfRa
				workBoot
				smellingSalts
				snakeOil
				lantern
				cheese
				garter
				dinoBone
				snakeLasso
				ankhMedallion
				pippin_sPad
				magnifier
				lightBulb
				watney_sFile
				warthogHairs
				bifocals
				redHair
				waterGlass
				carbonPaper
				yvette_sShoe
				grapes
				eveningGown
				charcoal
				wire
				mummy
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #modNum LBINV
			eachElementDo: #init
			state: NOCLICKHELP
		)
	)
)

(instance invWin of InsetWindow
	(properties
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open &tmp theWidth node obj)
		(= theWidth 0)
		(= node (inventory first:))
		(while node
			(if
				(not
					((= obj (NodeValue node)) isKindOf: InvItem)
				)
				(+= theWidth
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
			)
			(= node (inventory next: node))
		)
		(super open:)
		(invLook nsLeft: (/ (- (- right left) theWidth) 2))
	)
)

(instance ok of LBIconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		helpVerb V_HELP
	)
	
	(method (init)
		(super init:)
		(= lowlightColor myLowlightColor)
	)
)

(instance invLook of LBIconItem
	(properties
		view 991
		loop 2
		cel 0
		cursor 1
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		helpVerb V_HELP
	)
	
	(method (init)
		(super init:)
		(= lowlightColor myLowlightColor)
	)
)

(instance invHand of LBIconItem
	(properties
		view 991
		loop 0
		cel 0
		cursor 2
		message V_DO
		noun N_DO
		helpVerb V_HELP
	)
	
	(method (init)
		(super init:)
		(= lowlightColor myLowlightColor)
	)
)

(instance invHelp of LBIconItem
	(properties
		view 991
		loop 1
		cel 0
		cursor 9
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		noun N_HELP
		helpVerb V_HELP
	)
	
	(method (init)
		(super init:)
		(= lowlightColor myLowlightColor)
	)
)

(instance invSelect of LBIconItem
	(properties
		view 991
		loop 4
		cel 0
		cursor ARROW_CURSOR
		noun N_SELECT
		helpVerb V_HELP
	)
	
	(method (init)
		(super init:)
		(= lowlightColor myLowlightColor)
	)
)

(instance coupon of LBInvItem
	(properties
		view 83
		loop 1
		cursor 83
		message V_COUPON
		signal IMMEDIATE
		noun N_COUPON
	)
)

(instance claimTicket of LBInvItem
	(properties
		view 59
		loop 1
		cursor 59
		message V_CLAIM_TICKET
		signal IMMEDIATE
		noun N_CLAIM_TICKET
	)
)

(instance notebook of LBInvItem
	(properties
		view 50
		loop 1
		cursor 50
		message V_NOTEBOOK
		signal IMMEDIATE
		noun N_NOTEBOOK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(inventory hide:)
				(curRoom setInset: (ScriptID 20 0))
				(return)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance sandwich of LBInvItem
	(properties
		view 53
		loop 1
		cursor 53
		message V_SANDWICH
		signal IMMEDIATE
		noun N_SANDWICH
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_PRESS_PASS
				(super doVerb: theVerb 1)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance baseball of LBInvItem
	(properties
		view 54
		loop 1
		cursor 54
		message V_BASEBALL
		signal IMMEDIATE
		noun N_BASEBALL
	)
)

(instance deskKey of LBInvItem
	(properties
		view 52
		loop 1
		cursor 52
		message V_DESK_KEY
		signal IMMEDIATE
		noun N_DESK_KEY
	)
)

(instance pressPass of LBInvItem
	(properties
		view 51
		loop 1
		cursor 51
		message V_PRESS_PASS
		signal IMMEDIATE
		noun N_PRESS_PASS
	)
)

(instance pocketWatch of LBInvItem
	(properties
		view 2075
		cursor 75
		message V_POCKET_WATCH
		signal IMMEDIATE
		noun N_POCKET_WATCH
	)
)

(instance skeletonKey of LBInvItem
	(properties
		view 58
		loop 1
		cursor 58
		message V_SKELETON_KEY
		signal IMMEDIATE
		noun N_SKELETON_KEY
	)
)

(instance meat of LBInvItem
	(properties
		view 64
		loop 1
		cursor 64
		message V_MEAT
		signal IMMEDIATE
		noun N_MEAT
	)
)

(instance wireCutters of LBInvItem
	(properties
		view 76
		loop 1
		cursor 76
		message V_WIRECUTTERS
		signal IMMEDIATE
		noun N_WIRECUTTERS
	)
)

(instance daggerOfRa of LBInvItem
	(properties
		view 71
		loop 1
		cursor 71
		message V_DAGGER
		signal IMMEDIATE
		noun N_DAGGER
	)
)

(instance workBoot of LBInvItem
	(properties
		view 70
		loop 1
		cursor 70
		message V_BOOT
		signal IMMEDIATE
		noun N_BOOT
	)
)

(instance smellingSalts of LBInvItem
	(properties
		view 68
		loop 1
		cursor 68
		message V_SALTS
		signal IMMEDIATE
		noun N_SALTS
	)
)

(instance snakeOil of LBInvItem
	(properties
		view 61
		loop 1
		cursor 61
		message V_SNAKE_OIL
		signal IMMEDIATE
		noun N_SNAKE_OIL
		owner 520
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Message MsgGet LBINV noun V_TITLE NULL 1 @str)
				(narrator name: @str showTitle: TRUE)
				(cond 
					((== numSnakeOil 4)
						(messager say: N_SNAKE_OIL V_LOOK C_FULL 0 0 LBINV)
					)
					((== numSnakeOil 3)
						(messager say: N_SNAKE_OIL V_LOOK C_3_USES 0 0 LBINV)
					)
					((== numSnakeOil 2)
						(messager say: N_SNAKE_OIL V_LOOK C_2_USES 0 0 LBINV)
					)
					((== numSnakeOil 1)
						(messager say: N_SNAKE_OIL V_LOOK C_1_USE 0 0 LBINV)
					)
					(else
						(messager say: N_SNAKE_OIL V_LOOK C_EMPTY 0 0 LBINV)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance lantern of LBInvItem
	(properties
		view 84
		loop 1
		cursor 84
		message V_LANTERN
		signal IMMEDIATE
		noun N_LANTERN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== cel 0)
					(if (curRoom inset:)
						(messager say: noun V_DO C_CANT_USE_LANTERN 0 0 LBINV)
					else
						(inventory hide:)
						(curRoom setScript: sCrankLantern)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance cheese of LBInvItem
	(properties
		view 63
		loop 1
		cursor 63
		message V_CHEESE
		signal IMMEDIATE
		noun N_CHEESE
	)
)

(instance garter of LBInvItem
	(properties
		view 80
		loop 1
		cursor 80
		message V_GARTER
		signal IMMEDIATE
		noun N_GARTER
	)
)

(instance dinoBone of LBInvItem
	(properties
		view 65
		loop 1
		cursor 65
		message V_BONE
		signal IMMEDIATE
		noun N_BONE
	)
)

(instance snakeLasso of LBInvItem
	(properties
		view 62
		loop 1
		cursor 62
		message V_LASSO
		signal IMMEDIATE
		noun N_LASSO
	)
)

(instance ankhMedallion of LBInvItem
	(properties
		view 73
		loop 1
		cursor 73
		message V_ANKH
		signal IMMEDIATE
		noun N_ANKH
	)
)

(instance pippin_sPad of LBInvItem
	(properties
		view 79
		loop 1
		cel 1
		cursor 79
		message V_PIPPIN_PAD
		signal IMMEDIATE
		noun N_PIPPIN_PAD
		name "pippin'sPad"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(43
				(inventory hide:)
				(Bset fRubbedPad)
				(self cel: 0 signal: 2)
				(theGame setScript: sRubPad)
			)
			(V_LOOK
				(= noun (if (Btst fRubbedPad) N_PAD_AFTER_RUB else N_PAD_BEFORE_RUB))
				(super doVerb: theVerb)
			)
			(V_MAGNIFIER
				(= noun (if (Btst fRubbedPad) N_PAD_AFTER_RUB else N_PAD_BEFORE_RUB))
				(super doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance magnifier of LBInvItem
	(properties
		view 55
		loop 1
		cursor 55
		message V_MAGNIFIER
		signal IMMEDIATE
		noun N_MAGNIFIER
	)
)

(instance lightBulb of LBInvItem
	(properties
		view 67
		loop 1
		cursor 67
		message V_LIGHTBULB
		signal IMMEDIATE
		noun N_LIGHTBULB
	)
)

(instance watney_sFile of LBInvItem
	(properties
		view 72
		loop 1
		cursor 72
		message V_WATNEY_FILE
		signal IMMEDIATE
		noun N_WATNEY_FILE
		name "watney'sFile"
	)
)

(instance warthogHairs of LBInvItem
	(properties
		view 82
		loop 1
		cursor 82
		message V_WARTHOG_HAIR
		signal IMMEDIATE
		noun N_WARTHOG_HAIR
	)
)

(instance bifocals of LBInvItem
	(properties
		view 78
		loop 1
		cursor 78
		message V_BIFOCALS
		signal IMMEDIATE
		noun N_BIFOCALS
	)
)

(instance redHair of LBInvItem
	(properties
		view 74
		loop 1
		cursor 74
		message V_RED_HAIR
		signal IMMEDIATE
		noun N_RED_HAIR
	)
)

(instance waterGlass of LBInvItem
	(properties
		view 57
		loop 1
		cursor 57
		message V_WATERGLASS
		signal IMMEDIATE
		noun N_WATER_GLASS
	)
)

(instance carbonPaper of LBInvItem
	(properties
		view 77
		loop 1
		cel 1
		cursor 77
		message V_CARBON_PAPER
		signal IMMEDIATE
		noun N_CARBON_PAPER
	)
)

(instance yvette_sShoe of LBInvItem
	(properties
		view 85
		loop 1
		cursor 85
		message V_SHOE
		signal IMMEDIATE
		noun N_SHOE
		name "yvette'sShoe"
	)
)

(instance grapes of LBInvItem
	(properties
		view 81
		loop 1
		cursor 81
		message V_GRAPES
		signal IMMEDIATE
		noun N_GRAPES
	)
)

(instance eveningGown of LBInvItem
	(properties
		view 60
		loop 1
		cursor 60
		message V_GOWN
		signal IMMEDIATE
		noun N_GOWN
	)
)

(instance charcoal of LBInvItem
	(properties
		view 56
		loop 1
		cursor 56
		message V_CHARCOAL
		signal IMMEDIATE
		noun N_CHARCOAL
	)
)

(instance wire of LBInvItem
	(properties
		view 66
		loop 1
		cursor 66
		message V_WIRE
		signal IMMEDIATE
		noun N_WIRE
	)
)

(instance mummy of LBInvItem
	(properties
		view 87
		loop 1
		cursor 87
		message V_MUMMY
		signal IMMEDIATE
		noun N_MUMMY
	)
)

(instance sCrankLantern of Script
	
	(method (changeState newState &tmp theCur)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(lanternCrank
					init:
					posn: (- (ego x?) 10) (- (ego y?) 40)
					setCel: 0
					setLoop: 3
					setCycle: ForwardCounter 3 self
				)
			)
			(1
				(lanternCrank setLoop: 4 setCel: 0)
				(= ticks 120)
			)
			(2
				(lanternCrank dispose:)
				(lantern cel: (- 1 (lantern cel?)))
				(= theCur (if (lantern cel?) 88 else 84))
				(theIconBar
					curIcon: (theIconBar useIconItem?)
					curInvIcon: (lantern cursor: theCur yourself:)
					enable: (theIconBar at: ICON_ITEM)
				)
				(lanternTimer setReal: lanternTimer 0 3)
				((theIconBar curIcon?) cursor: theCur)
				(theGame
					handsOn:
					setCursor: ((theIconBar at: ICON_ITEM) cursor?)
				)
				(theIconBar select: (theIconBar at: ICON_ITEM))
				(self dispose:)
			)
		)
	)
)

(instance lanternCrank of Prop
	(properties
		view 84
		loop 3
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance sRubPad of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (Sound new:))
				(rubbingPad init: setLoop: 2 cel: 2)
				(= ticks 120)
			)
			(1
				(rubbingPad
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: OscRubPad 4 register self
				)
			)
			(2
				(rubbingPad setLoop: 2 cel: 4)
				(= ticks 60)
			)
			(3
				(theIconBar
					curIcon: (theIconBar useIconItem?)
					enable: (theIconBar at: 5)
				)
				(rubbingPad dispose:)
				(theGame handsOn:)
				(ego put: iCharcoal)
				(register dispose:)
				(self dispose:)
			)
		)
	)
)

(instance rubbingPad of Prop
	(properties
		x 130
		y 70
		view 566
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance lanternTimer of Timer
	
	(method (cue)
		(if (< curRoomNum 730)
			(lantern cel: 0 cursor: 84)
			(if (== (theIconBar curInvIcon?) lantern)
				((theIconBar useIconItem?) cursor: 84)
			)
			(if (== theCursor 88)
				(theGame setCursor: 84)
			)
		)
	)
)

(class OscRubPad of Oscillate
	(properties
		soundOsc 1
	)
	
	(method (init who howMany theSoundOsc whoCares)
		(if (>= argc 2)
			(= howManyCycles howMany)
			(if (>= argc 3)
				(= soundOsc theSoundOsc)
				(if (>= argc 4)
					(= caller whoCares)
				)
			)
		)
		(super init: who howMany whoCares)
	)
	
	(method (cycleDone)
		(soundOsc number: 51 play:)
		(super cycleDone:)
	)
)
