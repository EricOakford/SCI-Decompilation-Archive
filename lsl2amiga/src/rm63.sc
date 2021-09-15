;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm63 0
)

(enum ;emergency exit door states
	itsLocked
	pickedIt
	turnedIt
	openedIt
)

(instance rm63 of Room
	(properties
		picture 63
		west 62
	)
	
	(method (init)
		(Load VIEW currentEgoView)
		(Load VIEW 605)
		(Load VIEW 161)
		(Load VIEW 608)
		(Load SOUND 24)
		(super init:)
		(addToPics add: aDoor doit:)
		(aSmoker1
			setPri: 5
			cycleSpeed: 3
			setCycle: Forward
			stopUpd:
			init:
			setScript: smokerScript
		)
		(aSmoker2
			setPri: 8
			cycleSpeed: 2
			setCycle: Forward
			stopUpd:
			init:
		)
		(aSmoker3
			setPri: 7
			cycleSpeed: 3
			setCycle: Forward
			stopUpd:
			init:
		)
		(aJohnLight
			setPri: 3
			stopUpd:
			init:
		)
		(aJohnDoor
			setPri: 5
			stopUpd:
			init:
			setScript: johnScript
		)
		(aJohnUser1
			setPri: 0
			illegalBits: 0
			setCycle: Walk
			init:
			hide:
		)
		(aJohnUser2
			setPri: 0
			illegalBits: 0
			setCycle: Walk
			init:
			hide:
		)
		(aEmergencyExit
			illegalBits: 0
			setPri: 15
			setStep: 5 5
			stopUpd:
			init:
		)
		(NormalEgo loopE)
		(ego posn: 37 104 init: observeControl: cYELLOW)
		(self setRegions: AIRPLANE setScript: rm63Script)
	)
)

(instance rm63Script of Script
	(method (doit)
		(super doit:)
		(if (and (== currentStatus egoNORMAL) (& (ego onControl:) cBLUE))
			(curRoom newRoom: 62)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds (= cycles 0))
				(HandsOff)
				(ego posn: 250 144)
				(Print 63 36 #draw)
				(theGame changeScore: 6)
				(aEmergencyExit cycleSpeed: 3 setCycle: EndLoop self)
			)
			(2
				(aEmergencyExit
					setMotion: MoveTo 324 (aEmergencyExit y?) self
				)
				(Print 63 37 #draw)
			)
			(3
				(Print 63 38)
				(Print 63 39)
				(ego
					ignoreActors:
					illegalBits: 0
					view: 161
					posn: 261 134
					cycleSpeed: 1
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
					setPri: 15
				)
			)
			(4
				(ego setCel: setStep: 5 5 setMotion: MoveTo 333 135 self)
			)
			(5
				(curRoom newRoom: 64)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look,board,apply,open/(airport<bath),(airport<bath),bathroom')
			(cond 
				((ego inRect: 206 0 236 92)
					(Print 63 0)
				)
				((ego inRect: 242 90 254 102)
					(Print 63 1)
				)
				(else
					(Print 63 2)
				)
			)
		)
		(if (Said 'bang/door')
			(Print 63 3)
			(Print 63 4)
			(Print 63 5)
			(Print 63 6 #at -1 130)
		)
		(if (Said 'call')
			(Print (Format @str 63 7 introductoryPhrase))
			(Print 63 8)
			(Print 63 9)
			(Print 63 10)
		)
		(if (Said '(blow<up),blow/cord')
			(Print 63 11)
		)
		(if (Said 'look>')
			(if (Said '/door')
				(cond 
					((== emergencyExitState turnedIt)
						(Print 63 12)
					)
					((== emergencyExitState pickedIt)
						(Print 63 13)
					)
					(else
						(Print 63 14)
					)
				)
			)
			(if (Said '/carpet,ceiling')
				(Print 63 15)
			)
			(if (Said '/burn')
				(Print 63 16)
			)
			(if (Said '[/children,man,bimbo,airline,airport]')
				(Print 63 17)
				(Print 63 18)
				(Print 63 19)
			)
		)
		(if (Said 'smell')
			(Print 63 17)
			(Print 63 20)
			(Print 63 21)
		)
		(if (Said '(conceal<on),wear,afix,buckle,afix,apply/parachute')
			(cond 
				(wearingParachute
					(ItIs)
				)
				((not (ego has: iParachute))
					(DontHave)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(else
					(Ok)
					(Print 63 22)
					(= wearingParachute TRUE)
					(if (not wornParachute)
						(= wornParachute TRUE)
						(theGame changeScore: 4)
					)
				)
			)
		)
		(if (and wearingParachute (Said 'alter,(get<off),drain/parachute'))
			(Ok)
			(= wearingParachute FALSE)
		)
		(if (Said 'drain,apply,conceal/rejuvenator/bolt,door,cord')
			(cond 
				((not (ego has: iHairRejuvenator))
					(DontHave)
				)
				((not (ego inRect: 0 127 320 190))
					(NotClose)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(else
					(Print 63 23)
					(ego put: iHairRejuvenator -1)
					(theGame changeScore: -5)
				)
			)
		)
		(if (Said '/knife/bolt,door,cord')
			(cond 
				((not (ego has: iKnife))
					(DontHave)
				)
				((not (ego inRect: 0 127 320 190))
					(NotClose)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(else
					(Print 63 24)
				)
			)
		)
		(if
			(or
				(Said 'apply/bobbypin')
				(Said 'unbolt,open/bolt,cord/bobbypin')
				(Said 'get/bolt')
			)
			(cond 
				((not (ego inRect: 0 127 320 190))
					(NotClose)
				)
				((not (ego has: iBobbyPin))
					(Print 63 25)
					(Print 63 26 #at -1 130)
				)
				(else
					(theGame changeScore: 5)
					(Print 63 27)
					(Print 63 28)
					(= emergencyExitState pickedIt)
					(ego put: iBobbyPin -1)
				)
			)
		)
		(if (Said 'jerk,apply,jerk,jerk/cord,cord,button')
			(cond 
				((not (ego inRect: 0 127 320 190))
					(NotClose)
				)
				((== emergencyExitState pickedIt)
					(Print 63 29)
					(= emergencyExitState turnedIt)
				)
				((< emergencyExitState pickedIt)
					(Print 63 30)
				)
				(else
					(Print 63 31)
				)
			)
		)
		(if (Said 'jerk,open/door')
			(cond 
				((ego inRect: 206 0 236 92)
					(Print 63 0)
				)
				((ego inRect: 242 90 254 102)
					(Print 63 32)
				)
				((not (ego inRect: 0 127 320 190))
					(NotClose)
				)
				((== emergencyExitState turnedIt)
					(= emergencyExitState openedIt)
					(self changeState: 1)
				)
				((< emergencyExitState turnedIt)
					(Print 63 33)
				)
				(else
					(Print 63 31)
				)
			)
		)
		(if (Said 'man,(ask<for)/cigarette')	;EO: fixed said spec
			(Print 63 34)
			(Print 63 35 #at -1 130)
		)
	)
)

(instance smokerScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 1 5))
			)
			(1
				(aSmoker1 startUpd: setCel: 0 setCycle: EndLoop self)
			)
			(2
				(aSmoker1 stopUpd:)
				(= seconds (Random 1 5))
			)
			(3
				(aSmoker2 startUpd: setCel: 0 setCycle: EndLoop self)
			)
			(4
				(aSmoker2 stopUpd:)
				(= seconds (Random 1 5))
			)
			(5
				(aSmoker3 startUpd: setCel: 0 setCycle: EndLoop self)
			)
			(6
				(aSmoker3 stopUpd:)
				(= seconds (Random 1 5))
				(= state 0)
			)
		)
	)
)

(instance johnScript of Script
	(method (doit)
		(super doit:)
		(if (and (& (ego onControl:) cLRED) (== state 0))
			(johnScript changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds (= cycles 0))
				(aJohnDoor startUpd: setCycle: EndLoop self)
				(aJohnLight setCel: 0 forceUpd:)
			)
			(2
				(aJohnDoor stopUpd:)
				(aJohnUser1
					loop: 1
					posn: 219 85
					setPri: 5
					startUpd:
					show:
					setCycle: Walk
					setMotion: MoveTo 192 98 self
				)
				(aJohnUser2
					loop: 0
					posn: 174 103
					setPri: 6
					startUpd:
					show:
					setCycle: Walk
					setMotion: MoveTo 210 104
				)
			)
			(3
				(aJohnUser1 setMotion: MoveTo 174 98)
				(aJohnUser2 setMotion: MoveTo 222 86 self)
			)
			(4
				(aJohnUser1 hide:)
				(aJohnUser2 hide:)
				(aJohnDoor startUpd: setCycle: BegLoop self)
			)
			(5
				(aJohnDoor stopUpd:)
				(aJohnLight setCel: 1 forceUpd:)
				(= seconds (Random 11 33))
			)
			(6
				(= state 0)
			)
		)
	)
)

(instance aDoor of PicView
	(properties
		y 92
		x 253
		view 605
		loop 4
		signal ignrAct
	)
)

(instance aSmoker1 of Prop
	(properties
		y 84
		x 113
		view 605
		loop 2
	)
)

(instance aSmoker2 of Prop
	(properties
		y 114
		x 62
		view 605
		loop 2
		signal ignrAct
	)
)

(instance aSmoker3 of Prop
	(properties
		y 105
		x 155
		view 605
		loop 2
		signal ignrAct
	)
)

(instance aJohnLight of Prop
	(properties
		y 59
		x 203
		view 605
		loop 3
		cel 1
	)
)

(instance aJohnDoor of Prop
	(properties
		y 82
		x 232
		view 605
		loop 1
		signal ignrAct
	)
)

(instance aJohnUser1 of Actor
	(properties
		y 79
		x 222
		view 608
		signal ignrAct
	)
)

(instance aJohnUser2 of Actor
	(properties
		view 608
		signal ignrAct
	)
)

(instance aEmergencyExit of Actor
	(properties
		y 145
		x 274
		view 605
		signal ignrAct
	)
)
