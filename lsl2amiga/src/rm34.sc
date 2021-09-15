;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm34 0
)

(local
	drowningView
	henchwomanAppeared
	henchwomanWaiting
	oldEgoBase
	drowningInLeisureSuit
	henchwomanBeckons
)
(instance mDiving of Sound
	(properties
		number 21
		priority 100
		loop -1
	)
)

(instance rm34 of Room
	(properties
		picture 34
		horizon 5
	)
	
	(method (init)
		(Load VIEW 310)
		(Load VIEW 134)
		(Load VIEW 135)
		(Load VIEW 137)
		(Load VIEW 138)
		(Load VIEW 139)
		(Load SOUND 21)
		(super init:)
		(mDiving init:)
		(addToPics
			add: aView1 aView2 aView3 aView4 aView5 aView6 aView7 aView8
			doit:
		)
		(aWake
			setPri: 2
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			isExtra: TRUE
			init:
		)
		(aDrain
			setPri: 3
			setCycle: Forward
			cycleSpeed: 7
			ignoreActors:
			init:
		)
		(aHead2
			setPri: 10
			cycleSpeed: 2
			minPause: 20
			maxPause: 30
			ignoreActors:
			isExtra: TRUE
			init:
		)
		(aHead6
			setPri: 10
			cycleSpeed: 1
			minPause: 16
			maxPause: 40
			ignoreActors:
			init:
		)
		(aHead7
			setPri: 10
			cycleSpeed: 3
			minPause: 12
			maxPause: 31
			ignoreActors:
			init:
		)
		(self setRegions: SHIP setScript: rm34Script)
		(if (== currentEgoView 132)
			(self setRegions: HENCHWOMAN)
			(= henchView 311)
			(Load VIEW henchView)
			(aHench
				view: henchView
				illegalBits: cWHITE
				observeControl: cGREY
				setCycle: Walk
				init:
				setScript: henchScript
			)
		)
		(aMan
			setLoop: 4
			illegalBits: -513
			init:
			setMotion: Wander
			moveSpeed: 5
			cycleSpeed: 5
			setStep: 1 1
		)
		(if (== prevRoomNum 134)
			(= currentStatus egoSWIMMING)
			(ego
				view: 134
				setLoop: -1
				setCycle: Forward
				setPri: -1
				setMotion: 0
				setStep: 3 2
				illegalBits: -1
				ignoreControl: cLBLUE cGREY
				posn: 157 83
				cycleSpeed: 1
				moveSpeed: 1
				baseSetter: (= oldEgoBase BSetter)
				init:
			)
		else
			(NormalEgo loopN)
			(ego posn: 157 183 init:)
		)
		(User canControl: TRUE canInput: TRUE)
	)
	
	(method (dispose)
		(DisposeScript WANDER)
		(super dispose:)
	)
)

(instance rm34Script of Script
	(method (doit)
		(super doit:)
		(if (and (& (ego onControl:) cLGREEN) (== currentStatus egoNORMAL))
			(ego posn: 55 88)
			(self changeState: 1)
		)
		(if (and (& (ego onControl:) cLRED) (== currentStatus egoNORMAL))
			(ego posn: 255 88)
			(self changeState: 1)
		)
		(if (and (& (ego onControl:) cGREY) (== currentStatus egoNORMAL))
			(self changeState: 1)
		)
		(if (== SOUTH (ego edgeHit?))
			(if (== henchwomanWaiting 0)
				(curRoom newRoom: 31)
			else
				(Print 34 0)
				(Print 34 1 #draw)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 95)
			)
		)
		(if (and henchwomanIsHere henchwomanBeckons (> (ego y?) 181))
			(= henchwomanBeckons FALSE)
			(= henchwomanWaiting 1)
			(curRoom south: 95)
			(Print 34 2)
			(HandsOff)
			(ego setMotion: MoveTo (ego x?) 234)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(User canControl: FALSE)
				(= currentEgoView (ego view?))
				(= currentStatus egoDROWNING)
				(if (== currentEgoView vEgo)
					(= drowningInLeisureSuit TRUE)
					(= drowningView 137)
					(User canInput: FALSE)
				else
					(= drowningView 138)
				)
				(ego
					view: drowningView
					illegalBits: -1
					ignoreControl: cGREY cLBLUE
					cycleSpeed: 1
					moveSpeed: 1
					setStep: 2 2
					setCycle: Forward
					setMotion: Wander
				)
				(= seconds 5)
			)
			(2
				(= currentStatus egoSTOPPED)
				(User canInput: FALSE)
				(ego cel: 0 setCycle: EndLoop self setMotion: 0)
			)
			(3
				(if (== drowningInLeisureSuit TRUE)
					(Print 34 27)
				else
					(Print 34 28)
					(Print 34 29)
				)
				(= currentStatus egoDYING)
				(ego hide:)
			)
			(4
				(= seconds (= cycles 0))
				(= currentStatus egoSWIMMING)
				(User canControl: TRUE)
				(ego
					view: 134
					setStep: 3 2
					setLoop: -1
					setMotion: 0
					baseSetter: (= oldEgoBase BSetter)
				)
			)
			(5
				(= seconds (= cycles 0))
				(HandsOff)
				(= currentStatus egoDIVING)
				(ego
					view: 135
					loop: (if (or (== (ego loop?) 3) (== (ego loop?) 1))
						1
					else
						0
					)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego hide:)
				(curRoom newRoom: 134)
			)
			(7
				(= seconds (= cycles 0))
				(if (== sunscreenState sunscreenAPPLIED)
					(= sunscreenState sunscreenAFTERSWIM)
				)
				(ego
					view: currentEgoView
					setLoop: -1
					setPri: -1
					setMotion: 0
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors: 0
					illegalBits: cGREY
					observeControl: cLBLUE
					baseSetter: 0
				)
				(HandsOff)
				(= cycles 12)
			)
			(8
				(HandsOn)
				(Print 34 30)
				(NormalEgo)
			)
			(9
				(HandsOff)
				(if (not tookSwimInShipPool)
					(= tookSwimInShipPool TRUE)
					(theGame changeScore: 3)
				)
				(Ok)
				(if (< (ego y?) 113)
					(ego setMotion: MoveTo 160 112 self)
				else
					(self changeState: 10)
				)
			)
			(10
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 163 134 self
				)
			)
			(11
				(ego
					view: 139
					cycleSpeed: 1
					setMotion: 0
					setPri: 10
					setLoop: 0
					posn: 171 133
					cel: 0
					setCycle: EndLoop self
				)
			)
			(12
				(= currentStatus egoSITTING)
				(Print 34 31)
				(= seconds 5)
			)
			(13
				(if (== sunscreenState sunscreenAPPLIED)
					(User canInput: TRUE)
					(Print 34 32)
					(if (not henchwomanAppeared)
						(henchScript changeState: 1)
					)
				else
					(= currentStatus egoSTOPPED)
					(ego
						setLoop: 1
						setCel: 0
						cycleSpeed: 5
						setCycle: EndLoop self
					)
				)
			)
			(14
				(Print 34 33 #at -1 20 #draw)
				(= currentStatus egoDYING)
			)
			(15
				(= seconds (= cycles 0))
				(Ok)
				(ego cycleSpeed: 1 setCycle: BegLoop self)
				(if (== 1 (henchScript state?))
					(henchScript changeState: 255)
				)
			)
			(16
				(ego posn: 163 123)
				(NormalEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children')
				(Print 34 3)
			)
			(if (Said '/airport,barstool')
				(if (!= currentStatus egoSITTING)
					(Print 34 4)
				else
					(Print 34 5)
				)
			)
			(if (Said '<below/airport,barstool')
				(SeeNothing)
			)
			(if (Said '/inner')
				(Print 34 6)
			)
			(if (Said '/flower,palm')
				(Print 34 7)
			)
			(if (Said '[/fluid,craft,airport]')
				(cond 
					((== currentStatus egoSWIMMING)
						(Print 34 8)
					)
					((== currentStatus egoDROWNING)
						(Print 34 9)
					)
					((== currentStatus egoSITTING)
						(Print 34 10)
					)
					(else
						(Print 34 11)
						(Print 34 12)
					)
				)
			)
		)
		(if
			(or
				(Said '(get<in),board/fluid,fluid')
				(Said 'bathing')
			)
			(cond 
				((== currentStatus egoSWIMMING)
					(Print 34 13)
				)
				((!= currentStatus egoDROWNING)
					(Print 34 14)
					(Print 34 15)
				)
				(else
					(Ok)
					(self changeState: 4)
				)
			)
		)
		(if
			(or
				(Said 'disembark/fluid,fluid')
				(Said 'get,climb<off')
				(Said 'apply,climb/ladder')
			)
			(cond 
				((!= currentStatus egoSWIMMING)
					(NotNow)
				)
				((not (& (ego onControl:) cGREY))
					(NotClose)
				)
				(else
					(Ok)
					(self changeState: 7)
				)
			)
		)
		(if (or (Said 'board,bathing/underwater') (Said 'dive'))
			(cond 
				((== currentStatus egoDROWNING)
					(Print 34 16)
				)
				((!= currentStatus egoSWIMMING)
					(Print 34 17)
				)
				((& (ego onControl:) cGREY)
					(Print 34 18)
				)
				(else
					(Ok)
					(self changeState: 5)
					(mDiving play:)
				)
			)
		)
		(if
			(or
				(Said 'lie,board,bath[/bed,airport,barstool]')
				(Said 'bath[/down,airport,barstool]')
			)
			(cond 
				((== currentStatus egoSITTING)
					(Print 34 19)
				)
				((not (ego inRect: 139 102 199 134))
					(NotClose)
				)
				((!= currentEgoView 132)
					(Print 34 20)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(else
					(self changeState: 9)
				)
			)
		)
		(if
			(or
				(Said 'new,(new[<up]),(get<up)')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNORMAL)
					(Print 34 13)
				)
				(
					(and
						(<= (henchScript state?) 4)
						(== henchwomanIsHere TRUE)
					)
					(Print 34 21)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(self changeState: 15)
				)
			)
		)
		(if (or (Said 'breathe') (Said 'get/breath'))
			(Ok)
		)
		(if
			(or
				(Said 'jerk,jerk<over')
				(Said 'lie<on/front,stomach,ear')
			)
			(Print 34 22)
		)
		(if (Said '/bimbo>')
			(cond 
				((Said 'call/')
					(if henchwomanIsHere
						(Print (Format @str 34 23 introductoryPhrase))
					else
						(Print 34 24)
					)
				)
				((Said 'look/')
					(if henchwomanIsHere
						(Print 34 25)
					else
						(Print 34 26)
					)
				)
			)
		)
	)
)

(instance henchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= henchwomanAppeared TRUE)
				(= seconds (Random 5 10))
			)
			(2
				(if (!= currentStatus egoSITTING)
					(self changeState: 1)
				else
					(aHench setMotion: MoveTo 155 129 self)
					(= henchwomanIsHere TRUE)
					(NotifyScript HENCHWOMAN 1)
				)
			)
			(3
				(aHench setMotion: MoveTo 160 129 self)
			)
			(4
				(Print 34 34)
				(Print 34 35 #at -1 130)
				(= seconds 5)
			)
			(5
				(Print 34 36)
				(aHench setMotion: MoveTo 155 129 self)
				(= henchwomanBeckons TRUE)
			)
			(6
				(aHench setMotion: MoveTo 155 234 self)
			)
			(7
				(= seconds 10)
			)
			(8
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= henchwomanBeckons FALSE)
			)
		)
	)
)

(instance BSetter of Code
	(method (doit theActor)
		(theActor brBottom: (+ (theActor y?) 5))
		(theActor brTop: (- (theActor y?) 3))
		(theActor brLeft: (- (theActor x?) 10))
		(theActor brRight: (+ (theActor x?) 10))
	)
)

(instance aView1 of PicView
	(properties
		y 136
		x 11
		view 310
		loop 2
		cel 6
		priority 10
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 136
		x 45
		view 310
		loop 2
		priority 10
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 136
		x 80
		view 310
		loop 2
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 136
		x 115
		view 310
		loop 2
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 136
		x 177
		view 310
		loop 2
		cel 2
		priority 10
		signal ignrAct
	)
)

(instance aView6 of PicView
	(properties
		y 136
		x 224
		view 310
		loop 2
		cel 3
		priority 10
		signal ignrAct
	)
)

(instance aView7 of PicView
	(properties
		y 136
		x 263
		view 310
		loop 2
		cel 4
		priority 10
		signal ignrAct
	)
)

(instance aView8 of PicView
	(properties
		y 136
		x 303
		view 310
		loop 2
		cel 5
		priority 10
		signal ignrAct
	)
)

(instance aWake of Prop
	(properties
		y 53
		x 128
		view 310
	)
)

(instance aDrain of Prop
	(properties
		y 68
		x 119
		view 310
		loop 1
	)
)

(instance aHead2 of Extra
	(properties
		y 111
		x 49
		view 310
		loop 3
	)
)

(instance aHead6 of Extra
	(properties
		y 106
		x 228
		view 310
		loop 5
	)
)

(instance aHead7 of Extra
	(properties
		y 117
		x 259
		view 310
		loop 6
	)
)

(instance aMan of Actor
	(properties
		y 85
		x 194
		view 310
	)
)

(instance aHench of Actor
	(properties
		y 233
		x 155
	)
)
