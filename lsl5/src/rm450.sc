;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm450 0
)

(local
	local0
)
(instance rm450 of LLRoom
	(properties
		picture 450
		west 445
	)
	
	(method (init)
		(SetFFRoom 445)
		(HandsOff)
		(super init:)
		(self setScript: sRoom)
		(doc init:)
		(sheet init: hide:)
		(patti init: stopUpd:)
		(Load SOUND 455 752 753)
	)
)

(instance sRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego edgeHit: 4)
				(doc stopUpd:)
				(= seconds 2)
			)
			(1
				(Say Dr__Phil_Hopian 450 0 #dispose #caller self)
			)
			(2
				(Say Dr__Phil_Hopian 450 1 #dispose #caller self)
			)
			(3 (doc setCycle: CycleTo 2 1 self))
			(4
				(sheet show:)
				(doc setCycle: EndLoop self)
			)
			(5
				(doc hide:)
				(theMusic number: 450 setLoop: 1 flags: mNOPAUSE play: self)
			)
			(6
				(doc show: setCycle: CycleTo 2 -1 self)
			)
			(7
				(sheet hide:)
				(doc setCycle: BegLoop self)
			)
			(8 (= ticks 60))
			(9
				(doc setLoop: 2 stopUpd:)
				(= ticks 60)
			)
			(10
				(Say Dr__Phil_Hopian 450 2 #dispose #caller self)
			)
			(11 (= seconds 3))
			(12
				(doc setLoop: 0 stopUpd:)
				(= cycles 3)
			)
			(13
				(Say Dr__Phil_Hopian 450 3 #dispose #caller self)
			)
			(14
				(Say ego 450 4 #at -1 185)
				(= cycles 1)
			)
			(15
				(Say Dr__Phil_Hopian 450 5 #dispose #caller self)
			)
			(16
				(Say ego 450 6 #at -1 185)
				(= ticks 60)
			)
			(17
				(Say Dr__Phil_Hopian 450 7 #dispose #caller self)
			)
			(18
				(TimePrint 450 8 #at -1 185)
				(= cycles 10)
			)
			(19
				(doc setLoop: 3 setCel: 0)
				(= seconds 3)
			)
			(20
				(Say Dr__Phil_Hopian 450 9 #dispose #caller self)
			)
			(21 (= seconds 2))
			(22
				(TimePrint 450 10 #at -1 185)
				(= seconds 2)
			)
			(23
				(doc setCycle: EndLoop self)
				(sheet view: 452 show:)
			)
			(24
				(theMusic number: 451 setLoop: 1 flags: mNOPAUSE play: self)
			)
			(25
				(ShakeScreen 6 shakeSDown)
				(if (DoSound SoundOn)
					(soundFX number: 752 play:)
				)
				(= seconds 2)
			)
			(26 (doc setCycle: BegLoop self))
			(27
				(doc stopUpd:)
				(sheet stopUpd:)
				(= cycles 3)
			)
			(28
				(Say Dr__Phil_Hopian 450 11 #dispose #caller self)
			)
			(29 (= seconds 3))
			(30
				(sheet startUpd:)
				(doc setCycle: EndLoop self)
			)
			(31
				(theMusic number: 452 setLoop: 1 flags: 1 play: self)
			)
			(32
				(if (DoSound SoundOn)
					(soundFX number: 753 play:)
				)
				(ShakeScreen 6 3)
				(= seconds 2)
			)
			(33 (doc setCycle: BegLoop self))
			(34
				(doc stopUpd:)
				(sheet stopUpd:)
				(= seconds 2)
			)
			(35
				(Say Dr__Phil_Hopian 450 12 #dispose #caller self)
			)
			(36 (= seconds 2))
			(37
				(sheet startUpd:)
				(doc setCycle: EndLoop self)
			)
			(38
				(theMusic number: 453 setLoop: 1 flags: 1 play: self)
			)
			(39
				(sheet view: 450 show:)
				(doc setLoop: 0 setCel: 255 setCycle: CycleTo 2 -1 self)
			)
			(40
				(sheet hide:)
				(doc setCycle: BegLoop self)
			)
			(41
				(doc stopUpd:)
				(= cycles 3)
			)
			(42
				(doc
					view: 452
					setLoop: 0
					setCel: 0
					y: (+ (doc y?) 2)
					setCycle: CycleTo 8 1 self
				)
			)
			(43 (= seconds 2))
			(44
				(doc x: 166 y: 126 setCycle: EndLoop self)
			)
			(45
				(sheet view: 452 show:)
				(= ticks 67)
			)
			(46
				(soundFX number: 455 play:)
				(= ticks 350)
			)
			(47
				(doc y: (- (doc y?) 2) setLoop: 2 setCel: 0)
				(= seconds 2)
			)
			(48
				(sheet hide:)
				(doc setCel: 1)
				(= seconds 3)
			)
			(49
				(doc setCel: 0)
				(sheet show:)
				(= cycles 10)
			)
			(50 (doc hide:) (= cycles 10))
			(51
				(theMusic number: 454 setLoop: 1 flags: 1 play: self)
				(sheet dispose:)
				(patti dispose:)
				(curRoom style: 6 drawPic: 1)
				(= seconds 3)
			)
			(52
				(DoDisplay 2 myDisplayColor 450 13)
				(= seconds 3)
			)
			(53
				(HandsOff)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance doc of Prop
	(properties
		x 167
		y 98
		view 450
		cycleSpeed 30
	)
)

(instance sheet of View
	(properties
		x 166
		y 98
		view 450
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance patti of View
	(properties
		x 162
		y 149
		view 451
	)
)

(instance Dr__Phil_Hopian of Talker
	(properties
		x 30
		y 5
		nsTop 54
		nsLeft 145
		view 1450
		loop 1
		talkWidth 258
		name "Dr. Phil Hopian"
	)
	
	(method (init)
		(= eyes docEyes)
		(= mouth docMouth)
		(super init: &rest)
	)
)

(instance docEyes of Prop
	(properties
		nsTop 18
		nsLeft 13
		view 1450
		loop 2
		cycleSpeed 30
	)
)

(instance docMouth of Prop
	(properties
		nsTop 21
		nsLeft 14
		view 1450
		cycleSpeed 8
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
	)
)
