;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm103 0
	RollingLineOverlay 1
	PersonOnTVScreen 2
)

(local
	[responseToBarbara 30]
	userPrompt
	neonRight
	neonSign
	cameraScreen
	scanline
	biff
	cameraMan
	barbara
	loser1
	loser2
	larry
	chair
	applauseSignal
)
(procedure (RollingLineOverlay param1 theLoop)
	(scanline
		setLoop: theLoop
		setCel: 0
		cycleSpeed: 0
		setCycle: Forward
	)
	(param1 setCel: 0 cycleSpeed: 0 setCycle: Forward)
)

(procedure (PersonOnTVScreen person theCel)
	(person setCel: (if (> argc 1) theCel else 2) stopUpd:)
)

(instance theSound of Sound
	(properties
		number 108
		priority 100
		loop -1
	)
)

(instance rm103 of Room
	(properties
		picture 103
		horizon 1
	)
	
	(method (init)
		(Load VIEW 207)
		(Load VIEW 212)
		(Load VIEW 208)
		(Load VIEW 209)
		(Load VIEW 210)
		(Load SOUND 108)
		(super init:)
		(theSound init:)
		((= neonRight (Prop new:))
			view: 210
			loop: 1
			cel: 1
			posn: 97 53
			priority: 1
			ignoreActors:
			init:
			stopUpd:
		)
		((= neonSign (Prop new:))
			view: 210
			loop: 0
			cel: 0
			posn: 197 53
			priority: 1
			ignoreActors:
			init:
			stopUpd:
		)
		((= applauseSignal (Prop new:))
			view: 207
			setLoop: 5
			setCel: 0
			setPri: 15
			posn: 127 15
			init:
			stopUpd:
		)
		((= scanline (Prop new:))
			view: 209
			loop: 4
			cel: 0
			posn: 49 51
			setPri: 15
			ignoreActors:
			setCycle: Forward
			init:
		)
		((= cameraScreen (Prop new:))
			view: 210
			loop: 2
			posn: 276 5
			setPri: 15
			ignoreActors:
			isExtra: 1
			init:
			setCycle: Forward
		)
		((View new:)
			view: 207
			loop: 0
			cel: 0
			posn: 31 92
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((= biff (Prop new:))
			view: 207
			loop: 1
			cel: 0
			posn: 31 77
			setPri: 5
			ignoreActors:
			stopUpd:
			init:
		)
		((View new:)
			view: 208
			loop: 0
			cel: 3
			posn: 86 65
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((= barbara (Prop new:))
			view: 208
			setLoop: 3
			setCel: 0
			posn: 86 65
			setCycle: Forward
			setPri: 6
			ignoreActors:
			stopUpd:
			init:
		)
		((View new:)
			view: 208
			loop: 0
			cel: 0
			posn: 170 65
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((= loser1 (Prop new:))
			view: 208
			setLoop: 1
			setCel: 0
			posn: 170 65
			setPri: 6
			ignoreActors:
			init:
			stopUpd:
		)
		((= chair (Prop new:))
			view: 208
			setLoop: 0
			setCel: 4
			posn: 205 87
			setPri: 4
			ignoreActors:
			init:
			stopUpd:
		)
		((= larry (Prop new:))
			view: 208
			setLoop: 4
			setCel: 0
			posn: 205 65
			setPri: 5
			ignoreActors:
			init:
			hide:
		)
		((View new:)
			view: 208
			loop: 0
			cel: 1
			posn: 238 65
			setPri: 2
			ignoreActors:
			addToPic:
		)
		((= loser2 (Prop new:))
			view: 208
			setLoop: 2
			setCel: 0
			posn: 238 65
			setPri: 3
			ignoreActors:
			init:
			stopUpd:
		)
		((= cameraMan (Actor new:))
			view: 212
			loop: 0
			cel: 0
			posn: 86 143
			moveSpeed: 2
			cycleSpeed: 2
			setCycle: Walk
			setStep: 3 2
			init:
		)
		(ego
			view: 100
			loop: 1
			posn: 291 92
			setCycle: Walk
			setPri: 5
			init:
		)
		(HandsOff)
		(= currentStatus egoOnTVShow)
		(self setScript: rm103Script)
		(cameraMan setScript: minicamScript)
	)
)

(instance rm103Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 103 0)
				(ego setMotion: 0)
				(= seconds 4)
			)
			(1
				(Print 103 1)
				(ego setMotion: MoveTo 205 92 self)
				(minicamScript changeState: 7)
			)
			(2 (= seconds 2))
			(3
				(ego hide:)
				(chair posn: 205 65 setCel: 2 forceUpd:)
				(larry show:)
				(= seconds 4)
			)
			(4
				(theSound play:)
				(Print 103 2)
				(RollingLineOverlay biff 5)
				(Print 103 3 #draw)
				(neonRight setCycle: Forward)
				(neonSign setCycle: Forward)
				(applauseSignal setCycle: Forward)
				(minicamScript changeState: 3)
				(= seconds 4)
			)
			(5
				(Print 103 4)
				(Print 103 5)
				(PersonOnTVScreen biff)
				(scanline view: 209 setLoop: 3 setCel: 2 stopUpd:)
				(Print 103 6 #draw)
				(Print 103 7)
				(Print 103 8)
				(= seconds 4)
			)
			(6
				(minicamScript changeState: 7)
				(scanline view: 209 setLoop: 0 setCel: 2 forceUpd:)
				(Print 103 9 #draw)
				(Print 103 10)
				(= seconds 4)
			)
			(7
				(scanline view: 209 setLoop: 1 setCel: 2 forceUpd:)
				(Print 103 11 #draw)
				(Print 103 12)
				(Print 103 13)
				(= seconds 2)
			)
			(8
				(Print (Format @str 103 14 introductoryPhrase))
				(Print 103 15)
				(= seconds 3)
			)
			(9
				(scanline view: 209 setLoop: 2 setCel: 2 forceUpd:)
				(Print 103 16 #draw)
				(Print 103 17)
				(= seconds 2)
			)
			(10
				(neonRight stopUpd:)
				(neonSign stopUpd:)
				(applauseSignal stopUpd:)
				(theSound stop:)
				(RollingLineOverlay biff 5)
				(= seconds 2)
			)
			(11
				(Print 103 18)
				(PersonOnTVScreen biff)
				(RollingLineOverlay barbara 3)
				(minicamScript changeState: 3)
				(= seconds 4)
			)
			(12
				(Print 103 19)
				(Print 103 20)
				(Print 103 21 #at -1 152)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay loser1 0)
				(= seconds 4)
			)
			(13
				(Print 103 22)
				(Print 103 23)
				(Print 103 24 #at -1 152)
				(PersonOnTVScreen loser1)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(14
				(Print 103 25)
				(Print 103 26)
				(Print 103 27 #at -1 152)
				(minicamScript changeState: 7)
				(= seconds 4)
			)
			(15
				(Print 103 28)
				(PersonOnTVScreen barbara)
				(scanline view: 209 setLoop: 1 setCel: 1 stopUpd:)
				(larry setCel: 1 forceUpd:)
				(Print 103 29 #draw)
				(User canInput: TRUE)
				(= seconds 11)
			)
			(16
				(Print 103 30)
				(= state 15)
				(= seconds 11)
			)
			(17
				(= cycles 0)
				(User canInput: FALSE)
				(Print (Format @str 103 31 @responseToBarbara))
				(PersonOnTVScreen larry)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(18
				(Print 103 32)
				(= seconds 4)
			)
			(19
				(Print 103 33)
				(minicamScript changeState: 9)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay loser2 2)
				(= seconds 4)
			)
			(20
				(Print 103 34)
				(Print 103 35)
				(Print 103 36 #at -1 152)
				(PersonOnTVScreen loser2)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(21
				(minicamScript changeState: 3)
				(Print 103 37)
				(Print 103 38)
				(Print 103 39 #at -1 152)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay biff 5)
				(= seconds 4)
			)
			(22
				(Print 103 40)
				(PersonOnTVScreen biff)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(23
				(minicamScript changeState: 5)
				(Print 103 41)
				(Print 103 42)
				(Print 103 43)
				(Print 103 44 #at -1 152)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay loser1 0)
				(= seconds 4)
			)
			(24
				(Print 103 45)
				(Print 103 46 #at -1 152)
				(RollingLineOverlay barbara 3)
				(PersonOnTVScreen loser1)
				(= seconds 4)
			)
			(25
				(Print 103 47)
				(= seconds 4)
			)
			(26
				(minicamScript changeState: 7)
				(Print 103 48)
				(Print 103 49)
				(Print 103 50)
				(PersonOnTVScreen barbara)
				(scanline view: 209 setLoop: 1 setCel: 1 stopUpd:)
				(PersonOnTVScreen larry 1)
				(Print 103 51 #draw)
				(User canInput: TRUE)
				(= seconds 11)
			)
			(27
				(Print 103 52)
				(= state 26)
				(= seconds 11)
			)
			(28
				(= cycles 0)
				(User canInput: FALSE)
				(Print (Format @str 103 53 @responseToBarbara))
				(RollingLineOverlay barbara 3)
				(PersonOnTVScreen larry)
				(= seconds 4)
			)
			(29
				(Print 103 54)
				(= seconds 4)
			)
			(30
				(Print 103 55)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay loser2 2)
				(minicamScript changeState: 9)
				(= seconds 4)
			)
			(31
				(Print 103 56)
				(Print 103 57 #at -1 152)
				(RollingLineOverlay barbara 3)
				(PersonOnTVScreen loser2)
				(= seconds 4)
			)
			(32
				(Print 103 58)
				(RollingLineOverlay biff 5)
				(PersonOnTVScreen barbara)
				(= seconds 1)
			)
			(33
				(Print 103 59)
				(minicamScript changeState: 7)
				(PersonOnTVScreen biff)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(34
				(PersonOnTVScreen barbara 2)
				(Print 103 60 #draw)
				(Print 103 61 #at -1 152)
				(Print 103 62)
				(Print 103 63)
				(theSound loop: -1 play:)
				(PersonOnTVScreen barbara)
				(RollingLineOverlay biff 5)
				(neonRight setCycle: Forward)
				(neonSign setCycle: Forward)
				(applauseSignal setCycle: Forward)
				(theGame changeScore: 20)
				(= seconds 5)
			)
			(35
				(Print 103 64)
				(PersonOnTVScreen biff)
				(larry setLoop: 5 stopUpd:)
				(scanline setLoop: 7 setCel: 13)
				(Print 103 65 #draw #at -1 152)
				(Print 103 66)
				(barbara setCycle: Forward)
				(= seconds 1)
			)
			(36
				(Print 103 67)
				(RollingLineOverlay biff 5)
				(PersonOnTVScreen barbara 2)
				(= seconds 4)
			)
			(37
				(Print 103 68)
				(Print 103 69)
				(PersonOnTVScreen biff)
				(scanline
					setLoop: 6
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 2
				)
			)
			(38
				(Print 103 70)
				(Print 103 71 #at -1 152)
				(Print 103 72)
				(scanline setLoop: 7 setCel: 0 setCycle: EndLoop self)
			)
			(39
				(Print 103 73 #draw)
				(Print 103 74)
				(= seconds 4)
			)
			(40
				(Print 103 75)
				(larry setLoop: 4)
				(RollingLineOverlay barbara 3)
				(= seconds 4)
			)
			(41
				(Print 103 76)
				(PersonOnTVScreen barbara 2)
				(RollingLineOverlay biff 5)
				(= seconds 4)
			)
			(42
				(Print 103 77)
				(Print 103 78 #at -1 152)
				(Print 103 79)
				(theSound stop:)
				(PersonOnTVScreen biff)
				(neonRight stopUpd:)
				(neonSign stopUpd:)
				(applauseSignal stopUpd:)
				(scanline loop: 4 setCycle: Forward)
				(cameraMan setCycle: BegLoop)
				(= seconds 2)
			)
			(43
				(Print 103 80)
				(= seconds 2)
			)
			(44
				(= seconds 2)
				(larry hide:)
				(chair posn: 205 87 setCel: 4 forceUpd:)
				(ego loop: 0 show:)
			)
			(45
				(ego setMotion: MoveTo 291 92 self)
			)
			(46
				(Print 103 81)
				(curRoom newRoom: 102)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) keyDown)
				(<= SPACEBAR (event message?))
				(<= (event message?) 127)
				(or (== state 15) (== state 26))
			)
			(= userPrompt (User prompt?))
			(User
				prompt: {Give her your best line...}
				getInput: event
			)
			(User prompt: userPrompt)
			(Format @responseToBarbara (User inputLineAddr?))
			(if (< (StrLen @responseToBarbara) 7)
				(Format @responseToBarbara {Uh, I dunno.})
			)
			(if (== state 15) (self changeState: 17))
			(if (== state 26) (self changeState: 28))
			(return)
		)
	)
)

(instance minicamScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(cameraMan loop: 2 setCycle: EndLoop self)
			)
			(2 (cameraMan stopUpd:))
			(3 (cameraMan setCycle: BegLoop self))
			(4
				(cameraMan setCycle: Walk setMotion: MoveTo 86 143 self)
				(= state 0)
			)
			(5 (cameraMan setCycle: BegLoop self))
			(6
				(cameraMan setCycle: Walk setMotion: MoveTo 170 143 self)
				(= state 0)
			)
			(7 (cameraMan setCycle: BegLoop self))
			(8
				(cameraMan setCycle: Walk setMotion: MoveTo 205 143 self)
				(= state 0)
			)
			(9 (cameraMan setCycle: BegLoop self))
			(10
				(cameraMan setCycle: Walk setMotion: MoveTo 238 143 self)
				(= state 0)
			)
		)
	)
)
