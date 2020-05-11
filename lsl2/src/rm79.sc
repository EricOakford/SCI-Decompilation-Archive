;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include game.sh)
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
	local0
	ripple1
	ripple2
	local3
	helicopter
	branch
	oldEgoX
	oldEgoY
	keneewauwau
	keneewauwauHead
)
(instance theSound of Sound
	(properties)
)

(instance rm79 of Room
	(properties
		picture 79
		horizon 5
	)
	
	(method (init)
		(Load VIEW 725)
		(Load VIEW 103)
		(Load VIEW 179)
		(Load SOUND 1)
		(super init:)
		(theSound number: 1 init:)
		((= ripple1 (Prop new:))
			view: 725
			setLoop: 2
			posn: 27 187
			setPri: 8
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		((= ripple2 (Prop new:))
			view: 725
			setLoop: 1
			posn: 37 173
			setPri: 8
			setCycle: Forward
			init:
		)
		((= helicopter (Airplane new:))
			view: 725
			posn: 444 42
			setPri: 5
			setStep: 2 1
			startX: 444
			startY: 44
			endX: -44
			endY: 44
			init:
			setLoop: 0
			setCycle: Forward
		)
		((= branch (Actor new:))
			view: 725
			setLoop: 3
			setPri: 14
			setStep: 1 9
			posn: 202 58
			cycleSpeed: 2
			illegalBits: 0
			stopUpd:
			ignoreActors:
			init:
		)
		(self setScript: rm79Script)
		(NormalEgo)
		(if (== endGameState endMEETTRIBE)
			(= endGameState endGOTOVOLCANO)
			(= currentStatus egoMeetingTribe)
			(Load VIEW 710)
			((= keneewauwauHead (Prop new:))
				view: 710
				ignoreActors:
				setLoop: 4
				posn: 999 999
				setCycle: Forward
				setPri: 14
				init:
			)
			((= keneewauwau (Actor new:))
				view: 710
				setCycle: Walk
				cycleSpeed: 1
				moveSpeed: 1
				posn: 210 187
				init:
			)
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
			(and (== 3 (ego edgeHit?)) (== currentStatus egoNormal)) (curRoom newRoom: 76))
			(
			(and (& (ego onControl:) $0002) (== currentStatus egoNormal)) (ego setPri: 14) (self changeState: 19))
			(
			(and (& (ego onControl:) $0004) (== currentStatus egoNormal)) (ego setPri: 8) (self changeState: 19))
			((and debugging (== currentStatus egoNormal)) (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(keneewauwau setMotion: MoveTo 127 134 self)
			)
			(2
				(keneewauwau loop: 0)
				(= seconds 3)
			)
			(3
				(Print 79 10 #at -1 152)
				(keneewauwauHead
					posn: (+ (keneewauwau x?) -1) (+ (keneewauwau y?) -26)
				)
				(= seconds 3)
			)
			(4 (Print 79 11) (= seconds 3))
			(5
				(Print 79 12 #at -1 152)
				(Print 79 13)
				(Print 79 14)
				(theGame changeScore: 25)
				(= seconds 3)
			)
			(6
				(Print 79 15)
				(Print (Format @str 79 16 tritePhrase))
				(keneewauwauHead dispose:)
				(keneewauwau setMotion: MoveTo 270 234 self)
			)
			(7
				(keneewauwau dispose:)
				(NormalEgo)
				(++ endGameState)
			)
			(8
				(HandsOff)
				(ego view: 179 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(9
				(if (& (ego onControl: 1) $0008)
					(self changeState: 11)
				else
					(ego setCycle: BegLoop self)
				)
			)
			(10 (NormalEgo 3))
			(11
				(= currentStatus egoAuto)
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
				(branch setCycle: EndLoop self)
			)
			(12 (ego setCycle: EndLoop self))
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
					setCycle: EndLoop self
				)
				(branch setPri: 8 setMotion: MoveTo (branch x?) 234 self)
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
				(Print 79 26 #at -1 152)
				(ego setLoop: 3 setMotion: MoveTo 171 104 self)
			)
			(17
				(ego setPri: 2 setMotion: MoveTo 171 140 self)
			)
			(18 (curRoom newRoom: 80))
			(19
				(HandsOff)
				(Print 79 27 #at -1 20 #dispose)
				(= currentStatus egoFalling)
				(theSound play:)
				(ego
					view: 103
					illegalBits: 0
					ignoreActors:
					posn: (ego x?) (- (ego y?) 15)
					cel: 0
					setStep: 1 15
					setCycle: Forward
					setMotion: MoveTo (ego x?) (+ (ego y?) 200) self
				)
			)
			(20
				(cls)
				(= currentStatus egoStopped)
				(Print 79 28)
				(= currentStatus egoDead)
				(if (== debugging TRUE)
					(NormalEgo)
					(ego posn: oldEgoX oldEgoY)
					(self changeState: 10)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
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
		(if (Said 'hop') (Print 79 7) (Print 79 8 #at -1 152))
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
				((not (ego has: iVine)) (PrintDontHaveIt))
				((!= currentStatus egoNormal) (PrintNotNow))
				(else (self changeState: 8))
			)
		)
	)
)
