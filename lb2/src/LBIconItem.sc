;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include sci.sh)
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
	[local0 20]
)
(class LBIconItem of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
	)
	
	(method (ownedBy)
		(return 0)
	)
)

(class LBInvItem of InvI
	(properties
		view 0
		loop 0
		cel 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		state $0000
		cursor 999
		type $4000
		message 0
		modifiers $0000
		signal $0000
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum -1
		helpVerb 0
		owner 0
		script 0
		value 0
	)
	
	(method (init)
		(= lowlightColor myInsideColor)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(Message msgGET 15 noun 48 0 1 @local0)
		(narrator name: @local0 showTitle: 1)
		(cond 
			((and (> argc 1) theItem) (messager say: noun theVerb 0 0 0 15))
			((== theVerb 1) (messager say: noun 1 0 0 0 15))
			((== theVerb 8) (messager say: noun 8 0 0 0 15))
			((not (OneOf theVerb 3 4 2 6 12 13)) (messager say: 0 47 0 0 0 15))
			(else (super doVerb: theVerb))
		)
	)
)

(instance invCode of Code
	(properties)
	
	(method (init)
		(= inventory Inv)
		(Inv
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
			eachElementDo: #modNum 15
			eachElementDo: #init
			state: 2048
		)
	)
)

(instance invWin of InsetWindow
	(properties
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open &tmp temp0 inventoryFirst temp2)
		(= temp0 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(if
				(not
					((= temp2 (NodeValue inventoryFirst)) isKindOf: InvI)
				)
				(= temp0
					(+
						temp0
						(CelWide (temp2 view?) (temp2 loop?) (temp2 cel?))
					)
				)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(super open:)
		(invLook nsLeft: (/ (- (- right left) temp0) 2))
	)
)

(instance ok of LBIconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor 999
		signal $0043
		noun 17
		helpVerb 12
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
		message 1
		signal $0081
		noun 16
		helpVerb 12
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
		message 4
		noun 14
		helpVerb 12
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
		message 12
		signal $0003
		noun 15
		helpVerb 12
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
		cursor 999
		noun 18
		helpVerb 12
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
		message 10
		signal $0002
		noun 7
	)
)

(instance claimTicket of LBInvItem
	(properties
		view 59
		loop 1
		cursor 59
		message 5
		signal $0002
		noun 6
	)
)

(instance notebook of LBInvItem
	(properties
		view 50
		loop 1
		cursor 50
		message 14
		signal $0002
		noun 24
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(inventory hide:)
				(curRoom setInset: (ScriptID 20 0))
				(return)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sandwich of LBInvItem
	(properties
		view 53
		loop 1
		cursor 53
		message 15
		signal $0002
		noun 29
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11 (super doVerb: theVerb 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance baseball of LBInvItem
	(properties
		view 54
		loop 1
		cursor 54
		message 7
		signal $0002
		noun 1
	)
)

(instance deskKey of LBInvItem
	(properties
		view 52
		loop 1
		cursor 52
		message 16
		signal $0002
		noun 9
	)
)

(instance pressPass of LBInvItem
	(properties
		view 51
		loop 1
		cursor 51
		message 11
		signal $0002
		noun 27
	)
)

(instance pocketWatch of LBInvItem
	(properties
		view 2075
		cursor 75
		message 17
		signal $0002
		noun 26
	)
)

(instance skeletonKey of LBInvItem
	(properties
		view 58
		loop 1
		cursor 58
		message 18
		signal $0002
		noun 30
	)
)

(instance meat of LBInvItem
	(properties
		view 64
		loop 1
		cursor 64
		message 19
		signal $0002
		noun 22
	)
)

(instance wireCutters of LBInvItem
	(properties
		view 76
		loop 1
		cursor 76
		message 21
		signal $0002
		noun 38
	)
)

(instance daggerOfRa of LBInvItem
	(properties
		view 71
		loop 1
		cursor 71
		message 22
		signal $0002
		noun 8
	)
)

(instance workBoot of LBInvItem
	(properties
		view 70
		loop 1
		cursor 70
		message 23
		signal $0002
		noun 40
	)
)

(instance smellingSalts of LBInvItem
	(properties
		view 68
		loop 1
		cursor 68
		message 24
		signal $0002
		noun 31
	)
)

(instance snakeOil of LBInvItem
	(properties
		view 61
		loop 1
		cursor 61
		message 25
		signal $0002
		noun 33
		owner 520
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Message msgGET 15 noun 48 0 1 @local0)
				(narrator name: @local0 showTitle: 1)
				(cond 
					((== global150 4) (messager say: 33 1 9 0 0 15))
					((== global150 3) (messager say: 33 1 10 0 0 15))
					((== global150 2) (messager say: 33 1 11 0 0 15))
					((== global150 1) (messager say: 33 1 12 0 0 15))
					(else (messager say: 33 1 8 0 0 15))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lantern of LBInvItem
	(properties
		view 84
		loop 1
		cursor 84
		message 26
		signal $0002
		noun 19
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== cel 0)
					(if (curRoom inset:)
						(messager say: noun 4 4 0 0 15)
					else
						(inventory hide:)
						(curRoom setScript: sCrankLantern)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cheese of LBInvItem
	(properties
		view 63
		loop 1
		cursor 63
		message 27
		signal $0002
		noun 5
	)
)

(instance garter of LBInvItem
	(properties
		view 80
		loop 1
		cursor 80
		message 28
		signal $0002
		noun 12
	)
)

(instance dinoBone of LBInvItem
	(properties
		view 65
		loop 1
		cursor 65
		message 29
		signal $0002
		noun 10
	)
)

(instance snakeLasso of LBInvItem
	(properties
		view 62
		loop 1
		cursor 62
		message 30
		signal $0002
		noun 32
	)
)

(instance ankhMedallion of LBInvItem
	(properties
		view 73
		loop 1
		cursor 73
		message 31
		signal $0002
		noun 23
	)
)

(instance pippin_sPad of LBInvItem
	(properties
		view 79
		loop 1
		cel 1
		cursor 79
		message 32
		signal $0002
		noun 25
		name "pippin'sPad"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(43
				(inventory hide:)
				(Bset 35)
				(self cel: 0 signal: 2)
				(theGame setScript: sRubPad)
			)
			(1
				(= noun (if (Btst 35) 46 else 45))
				(super doVerb: theVerb)
			)
			(8
				(= noun (if (Btst 35) 46 else 45))
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance magnifier of LBInvItem
	(properties
		view 55
		loop 1
		cursor 55
		message 8
		signal $0002
		noun 21
	)
)

(instance lightBulb of LBInvItem
	(properties
		view 67
		loop 1
		cursor 67
		message 33
		signal $0002
		noun 20
	)
)

(instance watney_sFile of LBInvItem
	(properties
		view 72
		loop 1
		cursor 72
		message 34
		signal $0002
		noun 37
		name "watney'sFile"
	)
)

(instance warthogHairs of LBInvItem
	(properties
		view 82
		loop 1
		cursor 82
		message 35
		signal $0002
		noun 35
	)
)

(instance bifocals of LBInvItem
	(properties
		view 78
		loop 1
		cursor 78
		message 36
		signal $0002
		noun 2
	)
)

(instance redHair of LBInvItem
	(properties
		view 74
		loop 1
		cursor 74
		message 37
		signal $0002
		noun 28
	)
)

(instance waterGlass of LBInvItem
	(properties
		view 57
		loop 1
		cursor 57
		message 38
		signal $0002
		noun 36
	)
)

(instance carbonPaper of LBInvItem
	(properties
		view 77
		loop 1
		cel 1
		cursor 77
		message 39
		signal $0002
		noun 3
	)
)

(instance yvette_sShoe of LBInvItem
	(properties
		view 85
		loop 1
		cursor 85
		message 40
		signal $0002
		noun 41
		name "yvette'sShoe"
	)
)

(instance grapes of LBInvItem
	(properties
		view 81
		loop 1
		cursor 81
		message 41
		signal $0002
		noun 13
	)
)

(instance eveningGown of LBInvItem
	(properties
		view 60
		loop 1
		cursor 60
		message 42
		signal $0002
		noun 11
	)
)

(instance charcoal of LBInvItem
	(properties
		view 56
		loop 1
		cursor 56
		message 43
		signal $0002
		noun 4
	)
)

(instance wire of LBInvItem
	(properties
		view 66
		loop 1
		cursor 66
		message 44
		signal $0002
		noun 39
	)
)

(instance mummy of LBInvItem
	(properties
		view 87
		loop 1
		cursor 87
		message 9
		signal $0002
		noun 42
	)
)

(instance sCrankLantern of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
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
				(= temp0 (if (lantern cel?) 88 else 84))
				(theIconBar
					curIcon: (theIconBar useIconItem?)
					curInvIcon: (lantern cursor: temp0 yourself:)
					enable: (theIconBar at: 5)
				)
				(lanternTimer setReal: lanternTimer 0 3)
				((theIconBar curIcon?) cursor: temp0)
				(theGame
					handsOn:
					setCursor: ((theIconBar at: 5) cursor?)
				)
				(theIconBar select: (theIconBar at: 5))
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
		signal $0010
		cycleSpeed 12
	)
)

(instance sRubPad of Script
	(properties)
	
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
				(ego put: 33)
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
		signal $0010
	)
)

(instance lanternTimer of Timer
	(properties)
	
	(method (cue)
		(if (< curRoomNum 730)
			(lantern cel: 0 cursor: 84)
			(if (== (theIconBar curInvIcon?) lantern)
				((theIconBar useIconItem?) cursor: 84)
			)
			(if (== theCursor 88) (theGame setCursor: 84))
		)
	)
)

(class OscRubPad of Osc
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		howManyCycles -1
		soundOsc 1
	)
	
	(method (init param1 theHowManyCycles theSoundOsc theCaller)
		(if (>= argc 2)
			(= howManyCycles theHowManyCycles)
			(if (>= argc 3)
				(= soundOsc theSoundOsc)
				(if (>= argc 4) (= caller theCaller))
			)
		)
		(super init: param1 theHowManyCycles theCaller)
	)
	
	(method (cycleDone)
		(soundOsc number: 51 play:)
		(super cycleDone:)
	)
)
