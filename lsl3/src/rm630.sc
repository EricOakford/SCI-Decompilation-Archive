;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Wander)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm630 0
)
(synonyms
	(cable cable)
)

(instance rm630 of Room
	(properties
		picture 630
		horizon 1
		east 640
	)
	
	(method (init)
		(Load VIEW 720)
		(Load VIEW 631)
		(Load VIEW currentEgoView)
		(Load SOUND 630)
		(Load SOUND 4)
		(Load SOUND 5)
		(Load SOUND 631)
		(Load SOUND 699)
		(Load SCRIPT REVERSE)
		(Load SCRIPT WANDER)
		(super init:)
		(aGeneratorBottom init:)
		(aGeneratorTop init:)
		(aSwitch init:)
		(aLarry init:)
		(NormalEgo)
		(ego
			illegalBits: 0
			view: 632
			setLoop: 0
			setPri: 13
			cel: 0
			posn: 7 183
			init:
			baseSetter: SQ3Base
		)
		(self setScript: RoomScript)
		(= currentStatus 630)
		(music number: 630 loop: musicLoop play:)
	)
	
	(method (newRoom n)
		(DisposeScript WANDER)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (== currentStatus 630)
			(aLarry
				brLeft: (- (aLarry x?) 4)
				brRight: (+ (aLarry x?) 4)
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 19 171 setCycle: EndLoop self)
			)
			(1
				(ego
					posn: 35 149
					setLoop: 1
					cel: 1
					setCycle: Forward
					cycleSpeed: 4
					moveSpeed: 1
					setStep: 1 1
					setMotion: MoveTo 72 138 self
				)
			)
			(2
				(Printf 630 10 expletive)
				(ego
					observeControl: cLMAGENTA
					baseSetter: SQ3Base
					setMotion: MoveTo 187 99 self
				)
				(aLarry setMotion: MoveTo 6 185 self)
			)
			(3
				(aLarry
					view: 631
					setLoop: 0
					cel: 0
					setMotion: MoveTo 18 173
					setCycle: EndLoop self
				)
			)
			(4
				(Print 630 11)
				(aLarry
					posn: 31 150
					setLoop: 1
					cel: 1
					setCycle: Forward
					cycleSpeed: 4
					moveSpeed: 1
					setStep: 1 1
					setMotion: MoveTo 153 41 self
				)
				(HandsOn)
				(ego
					illegalBits: 0
					observeControl: cWHITE cLMAGENTA
					baseSetter: SQ3Base
				)
			)
			(5
				(aLarry observeControl: cWHITE cYELLOW setMotion: Wander 22)
			)
			(6
				(Ok)
				(HandsOff)
				(theGame changeScore: 40)
				(aSwitch setCycle: EndLoop self)
				(aLarry setMotion: MoveTo 148 (aLarry y?))
			)
			(7
				(aSwitch stopUpd:)
				(aGeneratorTop setCel: 0 stopUpd:)
				(if (== musicLoop SND_DONE)
					(music number: 631 loop: 1 play: self)
				else
					(= cycles 22)
				)
			)
			(8
				(Print 630 12)
				(if (== musicLoop SND_DONE)
					(= seconds 2)
				else
					(= cycles 22)
				)
			)
			(9
				(= currentStatus 2)
				(Print 630 13)
				(music number: 4 loop: 1 play:)
				(ego
					baseSetter: 0
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 3 4
					setMotion: MoveTo (ego x?) 152 self
				)
				(aLarry
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 3 4
					setMotion: MoveTo (aLarry x?) 152 self
				)
			)
			(10
				(ego posn: (ego x?) 181 loop: 2 cel: 0 setCycle: EndLoop)
			)
			(11
				(aLarry
					posn: (aLarry x?) 181
					loop: 2
					cel: 0
					setCycle: EndLoop
				)
				(= seconds 3)
			)
			(12
				(ego cycleSpeed: 1 loop: 3 cel: 0 setCycle: EndLoop)
				(aLarry cycleSpeed: 1 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(13
				(aLarry loop: 4 setCycle: Forward)
				(ego loop: 4 setCycle: Forward)
				(music number: 5 loop: 1 play: self)
			)
			(14
				(= currentStatus 0)
				(NormalEgo 3)
				(NormalActor aLarry 3 720)
				(aLarry setMotion: Follow ego 28)
				(music number: 699 loop: musicLoop play:)
				(Print 630 14)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'cable,attach,(on<drag)/cable,equipment,handle')
				(if (== currentStatus egoNORMAL)
					(ItIs)
				else
					(Print 630 0)
				)
			)
			((Said 'disconnect,drain,(off<drag),cease,drag/cable,equipment,handle')
				(cond 
					((!= currentStatus 630)
						(GoodIdea)
					)
					((not (ego inRect: 190 126 261 154))
						(NotClose)
					)
					((or (< (ego cel?) 6) (> (ego cel?) 9))
						(Print 630 1)
					)
					(else
						(RoomScript changeState: 6)
					)
				)
			)
			((Said 'get/cable,cable')
				(Print 630 2)
			)
			((Said 'look>')
				(cond 
					((Said '/cable,cable,cable')
						(if (== currentStatus 630)
							(Print 630 3)
						else
							(Print 630 4)
						)
					)
					((Said '/larry')
						(Print 630 5)
					)
					((Said '/equipment,equipment')
						(if (== currentStatus 630)
							(Print 630 6)
						else
							(Print 630 7)
						)
					)
					((Said '[/area]')
						(Print 630 8)
						(if (== currentStatus 630)
							(Print 630 9)
						)
					)
				)
			)
		)
	)
)

(instance aGeneratorBottom of View
	(properties
		y 149
		x 227
		view 630
	)
	
	(method (init)
		(super init:)
		(self setPri: 5 ignoreActors: stopUpd:)
	)
)

(instance aGeneratorTop of Prop
	(properties
		y 133
		x 235
		view 630
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 6 setCycle: Forward)
	)
)

(instance aSwitch of Prop
	(properties
		y 153
		x 213
		view 630
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 7 ignoreActors: stopUpd:)
	)
)

(instance aLarry of Actor
	(properties
		y 184
		x -28
		view 720
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward setPri: 5 ignoreActors:)
	)
)

(instance SQ3Base of Code
	(method (doit &tmp temp0)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) 18))
		(ego brRight: (+ (ego x?) 18))
	)
)
