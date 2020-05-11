;;; Sierra Script 1.0 - (do not remove this comment)
(script# 102)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm102 0
)

(local
	local0
	doorIsOpen
	assistantProducer
	leftDoor
	rightDoor
	leftScreen
	rightScreen
)
(instance rm102 of Room
	(properties
		picture 102
	)
	
	(method (init)
		(Load VIEW 202)
		(Load VIEW 206)
		(Load VIEW 217)
		(super init:)
		((View new:)
			view: 202
			loop: 4
			cel: 0
			posn: 59 126
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 202
			loop: 4
			cel: 1
			posn: 262 128
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((= leftScreen (Prop new:))
			view: 202
			setLoop: 2
			setCel: 0
			posn: 96 94
			setPri: 4
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= rightScreen (Prop new:))
			view: 202
			setLoop: 3
			setCel: 1
			posn: 228 95
			setPri: 4
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		(self setScript: rm102Script)
		(NormalEgo)
		(ego observeControl: 16384 8192)
		((= leftDoor (Prop new:))
			view: 202
			posn: 83 115
			setPri: 4
			init:
		)
		((= rightDoor (Prop new:))
			view: 202
			setLoop: 1
			posn: 250 116
			setPri: 4
			init:
		)
		(if (== prevRoomNum 103)
			(leftDoor setCel: 255)
			(ego posn: 67 122 observeControl: 8192 ignoreActors:)
			(rm102Script changeState: 12)
		else
			(ego posn: 163 153)
			(rm102Script changeState: 1)
		)
		(ego init:)
	)
)

(instance rm102Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== currentStatus egoSitting)
				(or (== state 20) (== state 1))
			)
			(self cue:)
		)
		(if (== doorIsOpen 8192) (ego ignoreControl: 8192))
		(if (== doorIsOpen 16384) (ego ignoreControl: 16384))
		(if (& (ego onControl:) $0004) (curRoom newRoom: 103))
		(if (& (ego onControl:) $0020) (curRoom newRoom: 104))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOn)
				(ego observeControl: 16384 8192)
			)
			(2 (= seconds (Random 10 30)))
			(3
				(if (!= currentStatus egoSitting)
					(-- state)
					(= seconds 3)
				else
					(HandsOff)
					(= doorIsOpen 16384)
					(leftDoor setCycle: EndLoop self)
				)
			)
			(4
				((= assistantProducer (Actor new:))
					view: 206
					loop: 0
					illegalBits: 0
					ignoreActors: 0
					setCycle: Walk
					posn: 60 119
					init:
					setMotion: MoveTo 80 120 self
				)
			)
			(5
				(assistantProducer
					setLoop: 2
					cel: 0
					setCycle: EndLoop
					setMotion: MoveTo 97 120 self
				)
			)
			(6
				(assistantProducer setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(7
				(assistantProducer setLoop: 4 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(Print 102 10 #at -1 20)
				(= seconds 2)
			)
			(9
				(Print 102 11 #at -1 20)
				(assistantProducer setLoop: 5 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(10
				(User canInput: TRUE)
				(if (!= currentStatus egoSitting) (User canControl: 1))
				(assistantProducer
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 0
					ignoreActors: 1
					setMotion: MoveTo 60 120
				)
			)
			(11 (assistantProducer dispose:))
			(12
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 120 123 self)
			)
			(13
				((= assistantProducer (Actor new:))
					view: 206
					loop: 0
					setCycle: Walk
					illegalBits: 0
					ignoreActors:
					posn: 60 119
					init:
					setMotion: MoveTo 80 120 self
				)
				(ego
					illegalBits: -32768
					observeControl: 16384 8192
					loop: 1
				)
			)
			(14
				(assistantProducer
					setLoop: 2
					cel: 0
					setCycle: EndLoop
					setMotion: MoveTo 97 120 self
				)
			)
			(15
				(assistantProducer setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(16
				(assistantProducer setLoop: 4 cycleSpeed: 1 setCycle: Forward)
				(= seconds 3)
			)
			(17
				(Print 102 12 #at -1 15 #width 280)
				(Print 102 13 #at -1 20)
				(Print 102 14 #at -1 20)
				(ego get: iCruiseTicket)
				(theGame changeScore: 6)
				(assistantProducer setLoop: 5 setCycle: EndLoop self)
			)
			(18
				(assistantProducer
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 36 120 self
				)
			)
			(19
				(assistantProducer posn: 264 121)
				(leftDoor setCycle: BegLoop self)
			)
			(20
				(HandsOn)
				(leftDoor stopUpd:)
				(= currentStatus egoNormal)
			)
			(21 (= seconds (Random 11 33)))
			(22
				(if (!= currentStatus egoSitting)
					(-- state)
					(= seconds 3)
				else
					(HandsOff)
					(= doorIsOpen 8192)
					(rightDoor setCycle: EndLoop self)
				)
			)
			(23
				(assistantProducer
					view: 217
					setCycle: Walk
					setMotion: MoveTo 219 122 self
				)
			)
			(24
				(assistantProducer loop: (+ (assistantProducer loop?) 2) setCycle: Forward)
				(= seconds 3)
			)
			(25
				(Print 102 15)
				(Print 102 16 #at -1 20)
				(Print 102 17)
				(assistantProducer loop: 0 setMotion: MoveTo 264 122 self)
			)
			(26
				(User canInput: TRUE)
				(if (== currentStatus egoNormal) (User canControl: TRUE))
				(assistantProducer dispose:)
				(Print 102 18 #at -1 152)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'open/door')
			(if doorIsOpen
				(PrintItIs)
			else
				(Print 102 0)
				(Print 102 1)
			)
		)
		(if (Said 'look>')
			(if (Said '/art,brick') (Print 102 2))
			(if (Said '/door')
				(if doorIsOpen
					(Print 102 3)
					(Print 102 4 #at -1 152)
				else
					(Print 102 5)
					(Print 102 6 #at -1 152)
				)
			)
			(if (Said '/krod,bimbo,computer') (Print 102 7))
			(if (Said '/barstool') (Print 102 8))
			(if (Said '[/airport,krod,lobby,building]')
				(Print 102 9)
			)
		)
		(if (Said 'bath')
			(cond 
				((== currentStatus egoSitting) (PrintYouAre))
				((not (ego inRect: 101 90 255 99)) (PrintNotCloseEnough))
				((!= currentStatus egoNormal) (PrintNotNow))
				(else
					(if (not satInGreenRoom)
						(= satInGreenRoom TRUE)
						(theGame changeScore: 1)
					)
					(PrintOk)
					(ego setScript: sittingScript)
					(sittingScript changeState: 0)
				)
			)
		)
		(if
			(or
				(Said 'new,(new<up),(get<up)')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNormal) (PrintYouAre))
				((!= currentStatus egoSitting) (PrintNotNow))
				(else
					(PrintOk)
					(ego setScript: sittingScript)
					(sittingScript changeState: 5)
				)
			)
		)
	)
)

(instance sittingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds (= cycles 0))
				(cond 
					((< (ego x?) 123) (ego setMotion: MoveTo 123 (ego y?) self))
					((> (ego x?) 200) (ego setMotion: MoveTo 200 (ego y?) self))
					(else (self cue:))
				)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 92 self)
			)
			(2
				(ego
					view: 101
					setLoop: 0
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(ego setCel: setMotion: 0 stopUpd:)
				(= currentStatus egoSitting)
				(User canInput: TRUE)
				(= seconds (Random 2 6))
			)
			(4
				(ego setLoop: (Random 1 2))
				(if (== (ego cel?) 0)
					(ego setCycle: EndLoop)
				else
					(ego setCycle: BegLoop)
				)
				(-- state)
				(= seconds (Random 2 6))
			)
			(5
				(HandsOff)
				(= seconds (= cycles 0))
				(ego
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
					setMotion: 0
				)
			)
			(6
				(NormalEgo 2)
				(ego observeControl: 16384 8192)
			)
		)
	)
)
