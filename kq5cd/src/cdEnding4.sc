;;; Sierra Script 1.0 - (do not remove this comment)
(script# 673)
(include sci.sh)
(use Main)
(use AudioScript)
(use KQ5Room)
(use RandCyc)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	cdEnding4 0
)

(local
	[local0 8]
	local8
	local9
	local10
	local11
)
(procedure (localproc_1116)
	(DrawPic 68 dpCLOSEREOPEN_HCENTER FALSE)
	(theEnd forceUpd:)
	(addToPics add: aCastle doit:)
)

(instance cdEnding4 of KQ5Room
	(properties
		picture 68
	)
	
	(method (init)
		(super init:)
		(Load rsSCRIPT 941)
		(Load rsSCRIPT 929)
		(Load rsVIEW 936)
		(LoadMany 132 699 799)
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
		(if (and (== (theMusic prevSignal?) -1) local11)
			(if (< (DoSound sndDISPOSE) 32)
				(theMusic number: 699 loop: -1 playBed:)
			else
				(theMusic number: 799 loop: -1 playBed:)
			)
		)
	)
)

(instance cartoon of AudioScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(syncIt init: hide: play: 10126)
				(= waitForCue 14592)
			)
			(2
				(theIconBar disable: 0 1 2 3 4 5)
				(family init: setCycle: End self)
			)
			(3
				(family cel: 0 setLoop: 9 setCycle: End self)
			)
			(4 (= waitForCue 14848))
			(5
				(egoHead init:)
				(valHead init:)
				(= waitForCue 15104)
			)
			(6
				(family setLoop: 2 setCycle: End self)
			)
			(7 (egoHead setCycle: End self))
			(8 (cls) (= waitForCue 15360))
			(9
				(cls)
				(egoHead setCycle: Beg)
				(valHead setLoop: 11 setCycle: RandCycle cycleSpeed: 4)
				(= waitForCue 15616)
			)
			(10
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
			(11
				(family setPri: 0 setMotion: MoveTo (family x?) 210 self)
			)
			(12
				(theEnd init:)
				(= seconds 4)
			)
			(13
				(theEnd setMotion: MoveTo 155 46 self)
			)
			(14
				(self setScript: creditsScript)
				(= cycles 1)
			)
			(15
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(16
				(theMusic prevSignal: -1)
				(= local11 1)
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
					673
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
					673
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
					(Format @temp0 673 1 score possibleScore)
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
					(Format @temp0 673 1 score possibleScore)
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(theIconBar enable:)
				(theIconBar disable: 0 1 2 3)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
				(Display
					673
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
					673
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
					673
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
					673
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
				(localproc_1116)
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

(instance syncIt of MonoAudioProp
	(properties)
)
