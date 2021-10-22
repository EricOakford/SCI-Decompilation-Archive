;;; Sierra Script 1.0 - (do not remove this comment)
(script# 483)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm483 0
)

(local
	[str 200]
)
(procedure (PattiSays &tmp seconds)
	(Print @str
		#at 10 5
		#width 290
		#mode teJustCenter
		#time (= seconds (PrintDelay @str))
		#dispose
	)
	(return (+ 3 seconds))
)

(instance rm483 of Room
	(properties
		picture 490
	)
	
	(method (init)
		(HandsOff)
		(Load SOUND 489)
		(Load SOUND 490)
		(Load SOUND 491)
		(Load SOUND 492)
		(super init:)
		(music number: 489 loop: 2 play:)
		(addToPics add: atpTelescope doit:)
		(self setScript: RoomScript)
		(aPatti setPri: 15 init:)
		(ego
			get: 12
			illegalBits: 0
			ignoreActors:
			view: 491
			posn: 173 1116
			setPri: 5
			setStep: 1 1
			cycleSpeed: 1
			moveSpeed: 1
			init:
		)
		(systemWindow color: vLGREY back: vBLACK)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(theGame setSpeed: 6)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aPatti setCycle: EndLoop self)
			)
			(1
				(= seconds 3)
			)
			(2
				(Format @str 483 1)
				(= seconds (PattiSays))
				(aPatti cycleSpeed: 2 loop: 1 cel: 0 setCycle: EndLoop)
			)
			(3
				(Format @str 483 2)
				(= seconds (PattiSays))
			)
			(4
				(Format @str 483 3)
				(= seconds (PattiSays))
				(aPatti setCycle: BegLoop)
			)
			(5
				(aSparkle init: ignoreActors: setCycle: EndLoop)
				(= seconds 2)
			)
			(6
				(Format @str 483 4)
				(= seconds (PattiSays))
			)
			(7
				(aPatti loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(8
				(aPatti stopUpd:)
				(= seconds 3)
			)
			(9
				(aHole0 setPri: 6 ignoreActors: init:)
				(aHole1 setPri: 5 ignoreActors: init:)
				(aHole2 setPri: 4 ignoreActors: init:)
				(= seconds 3)
			)
			(10
				(ego posn: 173 116 setMotion: MoveTo 212 116 self)
				(= cycles 11)
			)
			(11
				(music number: 490 loop: 2 play:)
				(Format @str 483 5)
				(PattiSays)
			)
			(12
				(ego setPri: 4 setMotion: MoveTo 212 110 self)
			)
			(13
				(Format @str 483 6)
				(= seconds (PattiSays))
				(ego setMotion: MoveTo 192 110)
			)
			(14
				(= cycles 11)
			)
			(15
				(Format @str 483 7)
				(= seconds (PattiSays))
			)
			(16
				(ego hide:)
				(aHole0 dispose:)
				(aHole1 dispose:)
				(aHole2 dispose:)
				(aPatti cycleSpeed: 2 setCycle: BegLoop self)
			)
			(17
				(= seconds 3)
			)
			(18
				(music number: 491 loop: 2 play:)
				(Format @str 483 8)
				(= seconds (PattiSays))
			)
			(19
				(= seconds 5)
			)
			(20
				(music fade:)
				(systemWindow color: myTextColor back: myBackColor)
				(TheMenuBar draw: state: TRUE)
				(StatusLine enable:)
				(Bclr fAutoSaveDisabled)
				(Bclr fCursorHidden)
				(curRoom newRoom: 484)
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
			(Print 483 0)
			(Bset fSkippedLoveScene)
			(curRoom newRoom: 484)
		)
	)
)

(instance aPatti of Actor
	(properties
		y 189
		x 170
		view 492
		cycleSpeed 1
	)
)

(instance atpTelescope of PicView
	(properties
		y 189
		x 160
		view 490
		priority 4
		signal ignrAct
	)
)

(instance aHole0 of View
	(properties
		y 92
		x 179
		view 490
		loop 1
	)
)

(instance aHole1 of View
	(properties
		y 92
		x 179
		view 490
		loop 1
		cel 1
	)
)

(instance aHole2 of View
	(properties
		y 92
		x 178
		view 490
		loop 1
		cel 2
	)
)

(instance aSparkle of Prop
	(properties
		y 9
		x 216
		view 490
		loop 2
	)
)
