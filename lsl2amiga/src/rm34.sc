;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include sci.sh)
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
	local2
	theBSetter
	drowningInLeisureSuit
	canFollowHenchwoman
)
(instance mDiving of Sound
	(properties
		number 21
		priority 100
		loop -1
	)
)

(instance rm34 of Rm
	(properties
		picture 34
		horizon 5
	)
	
	(method (init)
		(Load rsVIEW 310)
		(Load rsVIEW 134)
		(Load rsVIEW 135)
		(Load rsVIEW 137)
		(Load rsVIEW 138)
		(Load rsVIEW 139)
		(Load rsSOUND 21)
		(super init:)
		(mDiving init:)
		(addToPics
			add: aView1 aView2 aView3 aView4 aView5 aView6 aView7 aView8
			doit:
		)
		(aWake
			setPri: 2
			setCycle: Fwd
			cycleSpeed: 4
			ignoreActors:
			isExtra: 1
			init:
		)
		(aDrain
			setPri: 3
			setCycle: Fwd
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
			isExtra: 1
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
		(self setRegions: 300 setScript: rm34Script)
		(if (== currentEgoView 132)
			(self setRegions: 8)
			(= henchView 311)
			(Load rsVIEW henchView)
			(aHench
				view: henchView
				illegalBits: -32768
				observeControl: 256
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
			(= currentStatus 1006)
			(ego
				view: 134
				setLoop: -1
				setCycle: Fwd
				setPri: -1
				setMotion: 0
				setStep: 3 2
				illegalBits: -1
				ignoreControl: 512 256
				posn: 157 83
				cycleSpeed: 1
				moveSpeed: 1
				baseSetter: (= theBSetter BSetter)
				init:
			)
		else
			(NormalEgo 3)
			(ego posn: 157 183 init:)
		)
		(User canControl: 1 canInput: 1)
	)
	
	(method (dispose)
		(DisposeScript 970)
		(super dispose:)
	)
)

(instance rm34Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (& (ego onControl:) $0400) (== currentStatus 0))
			(ego posn: 55 88)
			(self changeState: 1)
		)
		(if
		(and (& (ego onControl:) $1000) (== currentStatus 0))
			(ego posn: 255 88)
			(self changeState: 1)
		)
		(if
		(and (& (ego onControl:) $0100) (== currentStatus 0))
			(self changeState: 1)
		)
		(if (== 3 (ego edgeHit?))
			(if (== local2 0)
				(curRoom newRoom: 31)
			else
				(Print 34 0)
				(Print 34 1 #draw)
				(= currentStatus 23)
				(curRoom newRoom: 95)
			)
		)
		(if
			(and
				henchwomanIsHere
				canFollowHenchwoman
				(> (ego y?) 181)
			)
			(= canFollowHenchwoman 0)
			(= local2 1)
			(curRoom south: 95)
			(Print 34 2)
			(HandsOff)
			(ego setMotion: MoveTo (ego x?) 234)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(User canControl: 0)
				(= currentEgoView (ego view?))
				(= currentStatus 1008)
				(if (== currentEgoView 100)
					(= drowningInLeisureSuit 1)
					(= drowningView 137)
					(User canInput: 0)
				else
					(= drowningView 138)
				)
				(ego
					view: drowningView
					illegalBits: -1
					ignoreControl: 256 512
					cycleSpeed: 1
					moveSpeed: 1
					setStep: 2 2
					setCycle: Fwd
					setMotion: Wander
				)
				(= seconds 5)
			)
			(2
				(= currentStatus 1000)
				(User canInput: 0)
				(ego cel: 0 setCycle: End self setMotion: 0)
			)
			(3
				(if (== drowningInLeisureSuit 1)
					(Print 34 27)
				else
					(Print 34 28)
					(Print 34 29)
				)
				(= currentStatus 1001)
				(ego hide:)
			)
			(4
				(= seconds (= cycles 0))
				(= currentStatus 1006)
				(User canControl: 1)
				(ego
					view: 134
					setStep: 3 2
					setLoop: -1
					setMotion: 0
					baseSetter: (= theBSetter BSetter)
				)
			)
			(5
				(= seconds (= cycles 0))
				(HandsOff)
				(= currentStatus 19)
				(ego
					view: 135
					loop: (if (or (== (ego loop?) 3) (== (ego loop?) 1))
						1
					else
						0
					)
					cel: 0
					setCycle: End self
				)
			)
			(6
				(ego hide:)
				(curRoom newRoom: 134)
			)
			(7
				(= seconds (= cycles 0))
				(if (== sunscreenState 1) (= sunscreenState 2))
				(ego
					view: currentEgoView
					setLoop: -1
					setPri: -1
					setMotion: 0
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors: 0
					illegalBits: 256
					observeControl: 512
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
					(= tookSwimInShipPool 1)
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
					setCycle: End self
				)
			)
			(12
				(= currentStatus 1009)
				(Print 34 31)
				(= seconds 5)
			)
			(13
				(if (== sunscreenState 1)
					(User canInput: 1)
					(Print 34 32)
					(if (not henchwomanAppeared)
						(henchScript changeState: 1)
					)
				else
					(= currentStatus 1000)
					(ego
						setLoop: 1
						setCel: 0
						cycleSpeed: 5
						setCycle: End self
					)
				)
			)
			(14
				(Print 34 33 #at -1 20 #draw)
				(= currentStatus 1001)
			)
			(15
				(= seconds (= cycles 0))
				(Ok)
				(ego cycleSpeed: 1 setCycle: Beg self)
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
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children') (Print 34 3))
			(if (Said '/airport,barstool')
				(if (!= currentStatus 1009)
					(Print 34 4)
				else
					(Print 34 5)
				)
			)
			(if (Said '<below/airport,barstool') (SeeNothing))
			(if (Said '/inner') (Print 34 6))
			(if (Said '/flower,palm') (Print 34 7))
			(if (Said '[/fluid,craft,airport]')
				(cond 
					((== currentStatus 1006) (Print 34 8))
					((== currentStatus 1008) (Print 34 9))
					((== currentStatus 1009) (Print 34 10))
					(else (Print 34 11) (Print 34 12))
				)
			)
		)
		(if
			(or
				(Said '(get<in),board/fluid,fluid')
				(Said 'bathing')
			)
			(cond 
				((== currentStatus 1006) (Print 34 13))
				((!= currentStatus 1008) (Print 34 14) (Print 34 15))
				(else (Ok) (self changeState: 4))
			)
		)
		(if
			(or
				(Said 'disembark/fluid,fluid')
				(Said 'get,climb<off')
				(Said 'apply,climb/ladder')
			)
			(cond 
				((!= currentStatus 1006) (NotNow))
				((not (& (ego onControl:) $0100)) (NotClose))
				(else (Ok) (self changeState: 7))
			)
		)
		(if
		(or (Said 'board,bathing/underwater') (Said 'dive'))
			(cond 
				((== currentStatus 1008) (Print 34 16))
				((!= currentStatus 1006) (Print 34 17))
				((& (ego onControl:) $0100) (Print 34 18))
				(else (Ok) (self changeState: 5) (mDiving play:))
			)
		)
		(if
			(or
				(Said 'lie,board,bath[/bed,airport,barstool]')
				(Said 'bath[/down,airport,barstool]')
			)
			(cond 
				((== currentStatus 1009) (Print 34 19))
				((not (ego inRect: 139 102 199 134)) (NotClose))
				((!= currentEgoView 132) (Print 34 20))
				((!= currentStatus 0) (NotNow))
				(else (self changeState: 9))
			)
		)
		(if
			(or
				(Said 'new,(new[<up]),(get<up)')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus 0) (Print 34 13))
				(
					(and
						(<= (henchScript state?) 4)
						(== henchwomanIsHere 1)
					)
					(Print 34 21)
				)
				((!= currentStatus 1009) (NotNow))
				(else (self changeState: 15))
			)
		)
		(if (or (Said 'breathe') (Said 'get/breath')) (Ok))
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
				((Said 'look/') (if henchwomanIsHere (Print 34 25) else (Print 34 26)))
			)
		)
	)
)

(instance henchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= henchwomanAppeared 1)
				(= seconds (Random 5 10))
			)
			(2
				(if (!= currentStatus 1009)
					(self changeState: 1)
				else
					(aHench setMotion: MoveTo 155 129 self)
					(= henchwomanIsHere 1)
					(NotifyScript 8 1)
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
				(= canFollowHenchwoman 1)
			)
			(6
				(aHench setMotion: MoveTo 155 234 self)
			)
			(7 (= seconds 10))
			(8
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere 0)
				(= canFollowHenchwoman 0)
			)
		)
	)
)

(instance BSetter of Code
	(properties)
	
	(method (doit param1)
		(param1 brBottom: (+ (param1 y?) 5))
		(param1 brTop: (- (param1 y?) 3))
		(param1 brLeft: (- (param1 x?) 10))
		(param1 brRight: (+ (param1 x?) 10))
	)
)

(instance aView1 of PV
	(properties
		y 136
		x 11
		view 310
		loop 2
		cel 6
		priority 10
		signal $4000
	)
)

(instance aView2 of PV
	(properties
		y 136
		x 45
		view 310
		loop 2
		priority 10
		signal $4000
	)
)

(instance aView3 of PV
	(properties
		y 136
		x 80
		view 310
		loop 2
		cel 1
		priority 10
		signal $4000
	)
)

(instance aView4 of PV
	(properties
		y 136
		x 115
		view 310
		loop 2
		cel 1
		priority 10
		signal $4000
	)
)

(instance aView5 of PV
	(properties
		y 136
		x 177
		view 310
		loop 2
		cel 2
		priority 10
		signal $4000
	)
)

(instance aView6 of PV
	(properties
		y 136
		x 224
		view 310
		loop 2
		cel 3
		priority 10
		signal $4000
	)
)

(instance aView7 of PV
	(properties
		y 136
		x 263
		view 310
		loop 2
		cel 4
		priority 10
		signal $4000
	)
)

(instance aView8 of PV
	(properties
		y 136
		x 303
		view 310
		loop 2
		cel 5
		priority 10
		signal $4000
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

(instance aMan of Act
	(properties
		y 85
		x 194
		view 310
	)
)

(instance aHench of Act
	(properties
		y 233
		x 155
	)
)
