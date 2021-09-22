;;; Sierra Script 1.0 - (do not remove this comment)
(script# 525)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm525 0
)

(local
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm525 of Room
	(properties
		picture 525
		horizon 1
	)
	
	(method (init)
		(Load VIEW 525)
		(Load SOUND 3)
		(Load SOUND 526)
		(Load SOUND 527)
		(Load SOUND 4)
		(HandsOff)
		(cls)
		(= saveSpeed (theGame setSpeed: 6))
		(super init:)
		(ego
			view: 525
			setLoop: (if (== currentStatus egoFALLING) 4 else 0)
			setCel: 0
			setStep: 0 7
			setMotion: 0
			setCycle: (if (== currentStatus egoFALLING) Forward else 0)
			posn: 142 13
			cycleSpeed: 1
			illegalBits: 0
			ignoreActors:
			init:
			put: 15 -1
		)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= currentStatus egoFALLING)
					(= cycles 10)
				else
					(music number: 4 loop: 1 play:)
					(ego setMotion: MoveTo 144 18 self)
					(= state 19)
				)
			)
			(1
				(Print 525 0)
				(Print 525 1 #at -1 144)
				(= cycles 20)
			)
			(2
				(music stop: number: 3 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(music stop: number: 526 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(5
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(6
				(ego setCycle: BegLoop self)
			)
			(7
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(8
				(music stop: number: 527 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(9
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(10
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(11
				(ego setCycle: BegLoop self)
			)
			(12
				(ego setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(13
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(14
				(music stop: number: 527 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(15
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(16
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(17
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(18
				(ego setLoop: 4 setPri: -1 posn: 143 114 setCycle: Forward)
				(aHose ignoreActors: init:)
				(Print 525 2 #at -1 10 #draw)
				(music stop: number: 4 loop: 1 play:)
				(curRoom newRoom: 530)
			)
			(20
				(ego setMotion: theJump self)
			)
			(21
				(Print 525 3)
				(ego hide:)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 814
					register: (Format @msgBuf 525 4)
					next: (Format @titleBuf 525 5)
				)
			)
		)
	)
)

(instance aHose of Prop
	(properties
		y 13
		x 142
		view 525
		loop 5
	)
)

(instance theJump of Jump
	(method (init)
		(super init: ego RoomScript)
		(self yStep: 1 y: 183)
	)
)
