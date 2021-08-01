;;; Sierra Script 1.0 - (do not remove this comment)
(script# 123)
(include sci.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	ending4 0
)

(local
	[local0 8]
	local8
	local9
	local10
)
(procedure (localproc_0ffa)
	(DrawPic 68 dpCLOSEREOPEN_HCENTER FALSE)
	(theEnd forceUpd:)
	(addToPics add: aCastle doit:)
)

(instance ending4 of KQ5Room
	(properties
		picture 68
	)
	
	(method (init)
		(super init:)
		(theGame setCursor: normalCursor 1)
		(addToPics add: aCastle doit:)
		(self setScript: cartoon)
	)
	
	(method (doit)
		(super doit:)
		(theGame setCursor: normalCursor 1)
		(if isVGA
			(= local9 7)
			(= local10 0)
			(= local8 1)
		else
			(= local9 15)
			(= local10 0)
			(= local8 0)
		)
		(if (== (theMusic prevSignal?) -1)
			(if (< (DoSound sndDISPOSE) 32)
				(theMusic number: 699 loop: -1 playBed:)
			else
				(theMusic number: 799 loop: -1 playBed:)
			)
		)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theIconBar disable: 0 1 2 3 4 5)
				(family init: setCycle: End self)
				(theAudio number: 8134 loop: 1 play:)
			)
			(2
				(family cel: 0 setLoop: 9 setCycle: End self)
			)
			(3
				(if (!= (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(4
				(egoHead init:)
				(valHead init:)
				(SpeakAudio 5236 self)
			)
			(5
				(family setLoop: 2 setCycle: End self)
			)
			(6 (egoHead setCycle: End self))
			(7
				(cls)
				(SpeakAudio 5237 self)
				(= seconds 5)
			)
			(8
				(cls)
				(egoHead setCycle: Beg)
				(valHead setLoop: 11 setCycle: MouthSync 5238)
				(SpeakAudio 5238 self)
			)
			(9
				(cls)
				(egoHead dispose:)
				(valHead dispose:)
				(family
					setLoop: 5
					moveSpeed: 2
					setCycle: Fwd
					setMotion: MoveTo (family x?) 175 self
				)
			)
			(10
				(family setPri: 0 setMotion: MoveTo (family x?) 210 self)
			)
			(11
				(theEnd init:)
				(= seconds 4)
			)
			(12
				(theEnd setMotion: MoveTo 155 46 self)
			)
			(13
				(self setScript: creditsScript)
			)
		)
	)
)

(instance aCastle of RPicView
	(properties
		x 157
		y 77
		view 934
		signal $4000
	)
)

(instance family of Actor
	(properties
		x 151
		y 183
		view 934
		loop 1
		priority 14
		signal $0010
		cycleSpeed 2
	)
)

(instance egoHead of Prop
	(properties
		x 145
		y 157
		view 934
		loop 3
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 13)
	)
)

(instance theEnd of Actor
	(properties
		x 155
		y 88
		view 934
		loop 6
		priority 15
		signal $4810
		illegalBits $0000
	)
)

(instance creditsScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 60])
		(switch (= state newState)
			(0
				(Display
					123
					0
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					0
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					(Format @temp0 123 1 score possibleScore)
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					(Format @temp0 123 1 score possibleScore)
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(1
				(localproc_0ffa)
				(Display
					123
					2
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					2
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					3
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					3
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 4)
			)
			(2
				(localproc_0ffa)
				(Display
					123
					4
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					4
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					5
					dsCOORD
					35
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					5
					dsCOORD
					34
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					6
					dsCOORD
					145
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					6
					dsCOORD
					144
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 12)
			)
			(3
				(localproc_0ffa)
				(Display
					123
					4
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					4
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					7
					dsCOORD
					35
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					7
					dsCOORD
					34
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					8
					dsCOORD
					145
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					8
					dsCOORD
					144
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 8)
			)
			(4
				(localproc_0ffa)
				(Display
					123
					9
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					9
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					10
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					10
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(5
				(localproc_0ffa)
				(Display
					123
					11
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					11
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					12
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					12
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 4)
			)
			(6
				(localproc_0ffa)
				(Display
					123
					13
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					13
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					14
					dsCOORD
					35
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					14
					dsCOORD
					34
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					15
					dsCOORD
					145
					80
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					15
					dsCOORD
					144
					79
					dsWIDTH
					130
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 12)
			)
			(7
				(localproc_0ffa)
				(Display
					123
					16
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					16
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					17
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					17
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 4)
			)
			(8
				(localproc_0ffa)
				(theIconBar enable:)
				(theIconBar disable: 0 1 2 3)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					19
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					19
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(9
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					20
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					20
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(10
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					21
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					21
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(11
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					22
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					22
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(12
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					23
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					23
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(13
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					24
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					24
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 10)
			)
			(14
				(localproc_0ffa)
				(Display
					123
					18
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					18
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					25
					dsCOORD
					90
					80
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					25
					dsCOORD
					89
					79
					dsWIDTH
					320
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 5)
			)
			(15
				(localproc_0ffa)
				(Display
					123
					26
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					26
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					27
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					27
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 5)
			)
			(16
				(localproc_0ffa)
				(Display
					123
					28
					dsCOORD
					90
					60
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					28
					dsCOORD
					89
					59
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(Display
					123
					29
					dsCOORD
					90
					80
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local10
					dsFONT
					local8
				)
				(Display
					123
					29
					dsCOORD
					89
					79
					dsWIDTH
					140
					dsALIGN
					1
					dsCOLOR
					local9
					dsFONT
					local8
				)
				(= seconds 5)
			)
			(17
				(localproc_0ffa)
				(self init:)
			)
		)
	)
)

(instance valHead of Prop
	(properties
		x 156
		y 158
		view 934
		loop 11
		cycleSpeed 3
	)
)
