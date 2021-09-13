;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include sci.sh)
(use Main)
(use AirplaneActor)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm79 0
)

(local
	[local0 2]
	egoX
	egoY
)
(instance theSound of Sound
	(properties)
)

(instance rm79 of Rm
	(properties
		picture 79
		horizon 5
	)
	
	(method (init)
		(Load rsVIEW 725)
		(Load rsVIEW 103)
		(Load rsVIEW 179)
		(Load rsSOUND 1)
		(super init:)
		(theSound number: 1 init:)
		(aWater1
			setLoop: 2
			setPri: 8
			cycleSpeed: 1
			setCycle: Fwd
			init:
		)
		(aWater2 setLoop: 1 setPri: 8 setCycle: Fwd init:)
		(aPlane
			setLoop: 0
			setPri: 5
			setStep: 2 1
			startX: 444
			startY: 44
			endX: -44
			endY: 44
			init:
			setCycle: Fwd
		)
		(aLimb
			setLoop: 3
			setPri: 14
			setStep: 1 9
			cycleSpeed: 2
			illegalBits: 0
			stopUpd:
			init:
		)
		(self setScript: rm79Script)
		(NormalEgo)
		(if (== endGameState 2)
			(= endGameState 3)
			(= currentStatus 11)
			(Load rsVIEW 710)
			(aMouth setLoop: 4 setCycle: Fwd setPri: 14 init:)
			(aChief setCycle: Walk cycleSpeed: 1 moveSpeed: 1 init:)
			(rm79Script changeState: 1)
		)
		(ego loop: 3 posn: 250 184 init:)
	)
)

(instance rm79Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (== 3 (ego edgeHit?)) (== currentStatus 0)) (curRoom newRoom: 76))
			(
			(and (& (ego onControl:) $0002) (== currentStatus 0)) (ego setPri: 14) (self changeState: 19))
			(
			(and (& (ego onControl:) $0004) (== currentStatus 0)) (ego setPri: 8) (self changeState: 19))
			((and debugging (== currentStatus 0)) (= egoX (ego x?)) (= egoY (ego y?)))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(aChief setMotion: MoveTo 127 134 self)
			)
			(2
				(aChief loop: 0)
				(= seconds 3)
			)
			(3
				(Print 79 10 #at -1 130)
				(aMouth posn: (+ (aChief x?) -1) (+ (aChief y?) -26))
				(= seconds 3)
			)
			(4 (Print 79 11) (= seconds 3))
			(5
				(Print 79 12 #at -1 130)
				(Print 79 13)
				(Print 79 14)
				(theGame changeScore: 25)
				(= seconds 3)
			)
			(6
				(Print 79 15)
				(Print (Format @str 79 16 tritePhrase))
				(aMouth dispose:)
				(aChief setMotion: MoveTo 270 234 self)
			)
			(7
				(aChief dispose:)
				(NormalEgo)
				(++ endGameState)
			)
			(8
				(HandsOff)
				(ego view: 179 loop: 0 cel: 0 setCycle: End self)
			)
			(9
				(if (& (ego onControl: 1) $0008)
					(self changeState: 11)
				else
					(ego setCycle: Beg self)
				)
			)
			(10 (NormalEgo 3))
			(11
				(= currentStatus 1)
				(ego
					loop: 1
					cel: 0
					posn: 174 54
					setPri: 10
					illegalBits: 0
					ignoreControl: 2 4
					cycleSpeed: 1
					put: 29 -1
				)
				(aLimb setCycle: End self)
			)
			(12 (ego setCycle: End self))
			(13
				(Print 79 17 #at -1 20 #draw)
				(theGame changeScore: 11)
				(= seconds 3)
			)
			(14
				(ego
					setLoop: 2
					cel: 0
					posn: 171 104
					setPri: 6
					setCycle: End self
				)
				(aLimb setPri: 8 setMotion: MoveTo (aLimb x?) 234 self)
			)
			(15
				(ego view: 100 setLoop: 2 cycleSpeed: 0 setCycle: Walk)
			)
			(16
				(Print 79 18 #draw)
				(Print 79 19)
				(Print 79 20)
				(Print 79 21)
				(Print 79 22)
				(Print 79 23)
				(Print 79 24)
				(Print 79 25)
				(Print 79 26 #at -1 130)
				(ego setLoop: 3 setMotion: MoveTo 171 104 self)
			)
			(17
				(ego setPri: 2 setMotion: MoveTo 171 140 self)
			)
			(18 (curRoom newRoom: 80))
			(19
				(HandsOff)
				(Print 79 27 #at -1 20 #dispose)
				(= currentStatus 12)
				(theSound play:)
				(ego
					view: 103
					illegalBits: 0
					ignoreActors:
					posn: (ego x?) (- (ego y?) 15)
					cel: 0
					setStep: 1 15
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (+ (ego y?) 200) self
				)
			)
			(20
				(cls)
				(= currentStatus 1000)
				(Print 79 28)
				(= currentStatus 1001)
				(if (== debugging 1)
					(NormalEgo)
					(ego posn: egoX egoY)
					(self changeState: 10)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/palm') (Print 79 0) (Print 79 1))
			(if (Said '/fern,leaf,bush') (Print 79 2))
			(if (Said '/airline') (Print 79 3))
			(if (Said '[/airport,canyon,/,path,canyon,boulder]')
				(Print 79 4)
				(Print 79 5)
			)
		)
		(if (Said '/branch') (Print 79 6))
		(if (Said 'hop') (Print 79 7) (Print 79 8 #at -1 130))
		(if (Said 'fill/bag/beach') (Print 79 9))
		(if
			(or
				(Said 'afix/landscape/bag')
				(Said 'afix/bag/landscape')
			)
			(Print 79 9)
		)
		(if (Said 'apply,throw/landscape')
			(cond 
				((not (ego has: 29)) (DontHave))
				((!= currentStatus 0) (NotNow))
				(else (self changeState: 8))
			)
		)
	)
)

(instance aWater1 of Prop
	(properties
		y 187
		x 27
		view 725
	)
)

(instance aWater2 of Prop
	(properties
		y 173
		x 37
		view 725
	)
)

(instance aPlane of Airplane
	(properties
		y 42
		x 444
		view 725
	)
)

(instance aLimb of Act
	(properties
		y 58
		x 202
		view 725
		signal $4000
	)
)

(instance aMouth of Prop
	(properties
		y 999
		x 999
		view 710
		signal $4000
	)
)

(instance aChief of Act
	(properties
		y 187
		x 210
		view 710
	)
)
