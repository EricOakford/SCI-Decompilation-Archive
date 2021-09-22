;;; Sierra Script 1.0 - (do not remove this comment)
(script# 482)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm482 0
)

(local
	[str 222]
)
(instance aWine of View
	(properties
		y 115
		x 162
		view 480
		loop 4
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setCel: 3 ignoreActors: stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 65
		x 159
		view 480
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors: stopUpd:)
	)
)

(instance aPanties of View
	(properties
		y 131
		x 63
		view 480
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aBra of View
	(properties
		y 131
		x 63
		view 480
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aPantyhose of View
	(properties
		y 141
		x 63
		view 480
		loop 1
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aDress of Prop
	(properties
		y 107
		x 39
		view 480
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 11
			setCel: (if (InRoom iDress 484) 0 else 255)
			ignoreActors:
			stopUpd:
		)
	)
)

(instance atpTelescope of PicView
	(properties
		y 50
		x 160
		view 480
		loop 3
		priority 1
		signal ignrAct
	)
)

(instance rm482 of Room
	(properties
		picture 480
	)
	
	(method (init)
		(HandsOff)
		(Load SCRIPT REVERSE)
		(Load VIEW 721)
		(Load SOUND 486)
		(Load SOUND 487)
		(Load SOUND 488)
		(super init:)
		(music number: 486 loop: 2 play:)
		(aWine init: setCel: 1 stopUpd:)
		(aDoor init:)
		(aPanties init:)
		(aBra init:)
		(aPantyhose init:)
		(aDress init:)
		(aBubbleLarry setScript: PrintLarry init:)
		(aBubblePatti setScript: PrintPatti init:)
		(addToPics add: atpTelescope doit:)
		(self setScript: RoomScript)
		(aPatti init:)
		(ego
			ignoreActors:
			illegalBits: 0
			setPri: 8
			view: 484
			loop: 0
			cycleSpeed: 4
			cel: 1
			setCycle: Forward
			posn: 178 105
			init:
		)
		(systemWindow color: myTextColor back: myBackColor)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(theGame setSpeed: 6)
	)
	
	(method (changeState newState &tmp len)
		(ChangeScriptState self newState 1 4)
		(switch (= state newState)
			(0
				(= cycles 33)
			)
			(1
				(Format @str 482 1)
				(PrintLarry cue:)
			)
			(2
				(= cycles 22)
			)
			(3
				(Format @str 482 2)
				(PrintPatti cue:)
			)
			(4
				(= cycles 22)
			)
			(5
				(Format @str 482 3)
				(PrintLarry cue:)
				(aBubblePatti cel: 0 setCycle: EndLoop)
			)
			(6
				(aBubblePatti setCycle: BegLoop)
				(= cycles 22)
			)
			(7
				(Format @str 482 4)
				(PrintLarry cue:)
			)
			(8
				(= cycles 22)
			)
			(9
				(Format @str 482 5)
				(PrintPatti cue:)
			)
			(10
				(= cycles 33)
			)
			(11
				(Format @str 482 6)
				(= seconds
					(= len (+ 3 (/ (StrLen @str) printTime)))
				)
				(Print @str #time len #dispose)
			)
			(12
				(= cycles 33)
			)
			(13
				(Format @str 482 7)
				(PrintPatti cue:)
			)
			(14
				(= cycles 33)
			)
			(15
				(Format @str 482 8)
				(= seconds
					(= len (+ 3 (/ (StrLen @str) printTime)))
				)
				(Print @str #time len #dispose)
			)
			(16
				(= cycles 3)
			)
			(17
				(music number: 487 loop: 2 play:)
				(aBubbleLarry cycleSpeed: 0)
				(Format @str 482 9)
				(PrintLarry cue:)
				(ego loop: 2 cel: 0 setCycle: CycleTo 3 1)
			)
			(18
				(= cycles 5)
			)
			(19
				(Format @str 482 10)
				(PrintLarry cue:)
			)
			(20
				(= cycles 5)
			)
			(21
				(Format @str 482 11)
				(PrintLarry cue:)
				(ego setCycle: EndLoop)
			)
			(22
				(= cycles 22)
			)
			(23
				(ego loop: 3 setCycle: EndLoop)
				(= cycles 44)
			)
			(24
				(Format @str 482 12)
				(PrintLarry cue:)
			)
			(25
				(= cycles 22)
			)
			(26
				(ego
					posn: 188 95
					setLoop: 4
					setCycle: Forward
					setStep: 1 1
					cycleSpeed: 0
					setMotion: MoveTo 193 90 self
				)
			)
			(27
				(ego
					posn: 196 103
					view: 721
					setLoop: 0
					setCel: 255
					setPri: -1
					cycleSpeed: 1
				)
				(= cycles 6)
			)
			(28
				(ego setCycle: BegLoop self)
			)
			(29
				(= cycles 11)
			)
			(30
				(NormalEgo loopE 720)
				(HandsOff)
				(ego setMotion: MoveTo 231 (ego y?) self)
			)
			(31
				(ego setMotion: MoveTo 231 120 self)
			)
			(32
				(ego setMotion: MoveTo 160 188 self)
			)
			(33
				(ego setMotion: MoveTo 160 444 self)
			)
			(34
				(aPatti cycleSpeed: 1 loop: 2 cel: 0 setCycle: EndLoop)
				(= cycles 33)
			)
			(35
				(aPatti setCycle: CycleTo 7 -1)
				(= cycles 22)
			)
			(36
				(aPatti setCycle: EndLoop)
				(= cycles 22)
			)
			(37
				(aPatti setCycle: CycleTo 7 -1 self)
			)
			(38
				(aPatti setCycle: EndLoop self)
			)
			(39
				(aPatti cycleSpeed: 0 setCycle: CycleTo 7 -1 self)
			)
			(40
				(aPatti setCycle: EndLoop self)
			)
			(41
				(music number: 488 loop: 2 play:)
				(aPatti posn: 155 98 setLoop: 3 cel: 0 setCycle: EndLoop)
				(= cycles 22)
			)
			(42
				(aBubblePatti cycleSpeed: 0)
				(Format @str 482 13)
				(PrintPatti cue:)
			)
			(43
				(= cycles 11)
			)
			(44
				(Format @str 482 14)
				(PrintPatti cue:)
			)
			(45
				(= cycles 33)
			)
			(46
				(Format @str 482 15)
				(PrintPatti cue:)
			)
			(47
				(= cycles 33)
			)
			(48
				(Format @str 482 16)
				(PrintPatti cue:)
			)
			(49
				(aPatti
					posn: 154 97
					cycleSpeed: 0
					setLoop: 4
					cel: 0
					setCycle: EndLoop self
					setMotion: MoveTo 148 89 self
				)
			)
			(50)
			(51
				(aPatti
					posn: 143 84
					setPri: -1
					setLoop: 5
					cel: 0
					setCycle: EndLoop self
				)
			)
			(52
				(aPatti
					posn: 143 84
					view: 482
					setLoop: -1
					loop: 3
					setCycle: Walk
					setMotion: MoveTo 159 65 self
				)
			)
			(53
				(aDoor setCycle: EndLoop self)
			)
			(54
				(aDoor stopUpd:)
				(aPatti setMotion: MoveTo 159 55 self)
			)
			(55
				(curRoom newRoom: 483)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event claimed?) FALSE)
				(== (event message?) `#8)
			)
			(Print 482 0)
			(Bset fSkippedLoveScene)
			(curRoom newRoom: 484)
		)
	)
)

(instance PrintLarry of Script
	(method (changeState newState)
		(ChangeScriptState self newState 2 4)
		(switch (= state newState)
			(0)
			(1
				(aBubbleLarry cel: 0 setCycle: EndLoop self)
			)
			(2
				(Print @str
					#at 10 5
					#width 290
					#mode teJustCenter
					#time (= seconds (PrintDelay @str))
					#dispose
				)
			)
			(3
				(aBubbleLarry setCycle: BegLoop)
				(= state 0)
				(RoomScript cue:)
			)
		)
	)
)

(instance PrintPatti of Script
	(method (changeState newState)
		(ChangeScriptState self newState 3 4)
		(switch (= state newState)
			(0)
			(1
				(aBubblePatti cel: 0 setCycle: EndLoop self)
			)
			(2
				(Print @str
					#at 10 5
					#width 290
					#mode teJustCenter
					#time (= seconds (PrintDelay @str))
					#dispose
				)
			)
			(3
				(aBubblePatti setCycle: BegLoop)
				(= state 0)
				(RoomScript cue:)
			)
		)
	)
)

(instance aBubblePatti of Prop
	(properties
		y 82
		x 141
		view 485
		loop 1
		cycleSpeed 1
	)
)

(instance aBubbleLarry of Prop
	(properties
		y 82
		x 179
		view 484
		loop 1
		cycleSpeed 1
	)
)

(instance aPatti of Actor
	(properties
		view 485
		cycleSpeed 4
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			posn: 155 98
			setPri: 8
			setCycle: Forward
		)
	)
)
