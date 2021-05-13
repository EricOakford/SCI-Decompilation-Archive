;;; Sierra Script 1.0 - (do not remove this comment)
(script# 750)
(include sci.sh)
(use Main)
(use LBRoom)
(use Print)
(use Talker)
(use RandCyc)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm750 0
	Coroner 21
)

(local
	local0 =  1
	local1
	local2
	local3
	local4 =  1
	local5 =  1
)
(procedure (localproc_013c)
	(= local1
		(Print
			font: 69
			addText: 5 0 0 1 (WhichLanguage 0 0 42 0 0) 0
			addButton: 1 4 0 0 1 0 17
			addButton: 2 4 0 0 2 0 34
			addButton: 3 4 0 0 3 112 34
			addButton: 4 4 0 0 4 0 51
			addButton: 5 4 0 0 5 112 51
			addButton: 6 4 0 0 6 0 68
			addButton: 8 4 0 0 8 112 68
			addButton: 7 4 0 0 7 0 85
			addButton: 9 4 0 0 9 0 102
			addButton: 10 4 0 0 10 0 119
			addButton: 11 4 0 0 11 0 136
			addButton: 12 4 0 0 12 0 153
			init:
		)
	)
)

(procedure (localproc_0221)
	(= local2
		(Print
			addText: 5 0 0 2 (WhichLanguage 0 0 67 0 0) 0
			addButton: 1 6 0 0 1 0 17
			addButton: 2 6 0 0 2 (WhichLanguage 128 80 128 80 96) 17
			addButton: 3 6 0 0 3 0 34
			addButton: 4 6 0 0 4 (WhichLanguage 80 80 128 80 80) 34
			addButton: 5 6 0 0 5 0 51
			addButton: 6 6 0 0 6 (WhichLanguage 80 80 128 80 80) 51
			addButton: 7 6 0 0 7 0 68
			addButton: 8 6 0 0 8 0 85
			addButton: 9 6 0 0 9 0 102
			addButton: 10 6 0 0 10 (WhichLanguage 0 0 8 0 0) 119
			addButton: 11 6 0 0 11 0 136
			addButton: 12 6 0 0 12 0 153
			addButton: 13 6 0 0 13 (WhichLanguage 48 48 64 48 80) 153
			init:
		)
	)
)

(instance rm750 of LBRoom
	(properties
		picture 750
		style $000a
	)
	
	(method (init)
		(LoadMany 129 760)
		(LoadMany 132 760 120)
		(LoadMany 128 1750 752 753 760)
		(Bset 147)
		(super init:)
		(ego wearingGown: 0)
		(theIconBar disable:)
		(theMusic number: 120 flags: 1 loop: -1 play:)
		(reporters init:)
		(reporterHeads init:)
		(r_arm init: setCycle: RandCycle cycleSpeed: 48)
		(l_arm init: setCycle: RandCycle cycleSpeed: 48)
		(curRoom setScript: sBeforeAsking)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sBeforeAsking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theGame setCursor: 999)
				(messager say: 2 0 0 0 self)
			)
			(2
				(= next sFirstFive)
				(self dispose:)
			)
		)
	)
)

(instance sFirstFive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 0 0 local0 self)
			)
			(1
				(localproc_013c)
				(if (!= local1 9) (= local4 0))
				(localproc_0221)
				(cond 
					((!= local0 4) (if (and (!= local2 9) (!= local2 10)) (= local4 0)))
					((and (!= local2 6) (!= local2 3)) (= local4 0))
				)
				(if (< (++ local0) 6)
					(self changeState: 0)
				else
					(= cycles 2)
				)
			)
			(2
				(if (Btst 147)
					(= next sSixToNine)
				else
					(= local4 0)
					(= next sTenOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance sSixToNine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 6) (= cycles 1))
			(1
				(messager say: 1 0 0 local0 self)
			)
			(2
				(localproc_013c)
				(switch local0
					(6
						(if (!= local1 1)
							(= local4 0)
							(self changeState: 4)
						else
							(++ local0)
						)
					)
					(9
						(if (!= local1 9) (= local4 0))
						(++ local0)
					)
					(else 
						(if (!= local1 6) (= local4 0))
						(++ local0)
					)
				)
				(if (< local0 10)
					(self changeState: 1)
				else
					(= cycles 1)
				)
			)
			(3
				(localproc_0221)
				(if (and (!= local2 9) (!= local2 10)) (= local4 0))
				(= cycles 1)
			)
			(4
				(= next sTenOn)
				(self dispose:)
			)
		)
	)
)

(instance sTenOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 10) (= cycles 1))
			(1
				(messager say: 1 0 0 local0 self)
			)
			(2
				(localproc_013c)
				(switch local0
					(10
						(if (!= local1 6)
							(= local5 0)
							(= local0 (+ local0 2))
						else
							(++ local0)
						)
						(= cycles 1)
					)
					(11
						(if (!= local1 9) (= local5 0))
						(++ local0)
						(= cycles 1)
					)
					(12
						(if (!= local1 11)
							(= local0 15)
							(messager say: 8 0 6 0 self)
						else
							(++ local0)
							(messager say: 8 0 5 0 self)
						)
					)
					(13
						(if (!= local1 6)
							(= local0 15)
							(messager say: 8 0 6 0 self)
						else
							(++ local0)
							(messager say: 8 0 5 0 self)
						)
					)
					(14
						(if (!= local1 12)
							(messager say: 8 0 6 0 self)
						else
							(messager say: 8 0 5 0 self)
						)
						(++ local0)
					)
					(15
						(if (!= local1 8)
							(messager say: 9 0 0 0 self)
						else
							(messager say: 9 0 7 0 self)
						)
						(++ local0)
					)
					(16
						(if (!= local1 5)
							(messager say: 10 0 0 0 self)
						else
							(messager say: 10 0 8 0 self)
						)
						(++ local0)
					)
				)
			)
			(3
				(if (> local0 16)
					(= cycles 1)
				else
					(self changeState: 1)
				)
			)
			(4
				(= next sAfterQuestions)
				(self dispose:)
			)
		)
	)
)

(instance sAfterQuestions of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0
				(cond 
					(
						(and
							(ego has: 31)
							(ego has: 26)
							(ego has: 27)
							(ego has: 10)
							local4
							local5
							(ego has: 11)
						)
						(= local3 2)
					)
					(
						(and
							(ego has: 31)
							(ego has: 26)
							(ego has: 27)
							(ego has: 10)
							local4
						)
						(= local3 1)
					)
					(local5 (= local3 4))
					((ego has: 11) (= local3 10))
					(else (= local3 3))
				)
				(cond 
					(
						(and
							(ego has: 31)
							(ego has: 26)
							(ego has: 27)
							(ego has: 10)
							local4
							local5
							(ego has: 11)
						)
						(= global126 1)
					)
					(
						(and
							(ego has: 31)
							(ego has: 26)
							(ego has: 27)
							(ego has: 10)
							local4
						)
						(= global126 4)
					)
					(local5 (= global126 2))
					(else (= global126 3))
				)
				(if (or (== local3 1) (== local3 2))
					(tut init:)
					(messager say: 7 0 0 0 self)
				else
					(self changeState: 3)
				)
			)
			(1
				(tut setMotion: MoveTo -10 113 self)
			)
			(2 (messager say: 7 0 9 0 self))
			(3
				(messager say: 3 0 local3 0 self)
			)
			(4
				(cond 
					((== global126 1)
						(theMusic2 number: 750 flags: 1 loop: 1 play:)
						(reporterHeads setCycle: End self)
					)
					((or (== global126 2) (== global126 4))
						(theMusic2 number: 751 flags: 1 loop: 1 play:)
						(reporterHeads setCycle: End self)
					)
					(else
						(theMusic2 number: 752 flags: 1 loop: 1 play:)
						(= seconds 4)
					)
				)
			)
			(5
				(if (== global126 3)
					(self changeState: 8)
				else
					(reporterHeads setLoop: 2 setCel: 0 setCycle: End self)
				)
			)
			(6
				(reporterHeads setLoop: 3 setCel: 0 setCycle: End self)
			)
			(7
				(reporterHeads setCel: 0 setLoop: 4 setCycle: Fwd)
				(= seconds 4)
			)
			(8
				(paper init: hide:)
				(headline
					init:
					loop: (if (OneOf global126 2 4) 2 else global126)
					hide:
				)
				(curRoom drawPic: 780)
				(cast eachElementDo: #hide)
				(= cycles 2)
				(theMusic fade: 0 12 30 1)
			)
			(9
				(theMusic number: 760 flags: 1 loop: 1 play:)
				(paper show:)
				(paper setCycle: End self)
			)
			(10
				(curRoom drawPic: 760 100)
				(paper hide:)
				(= cycles 1)
			)
			(11
				(headline show:)
				(= seconds 4)
			)
			(12
				(curRoom
					newRoom:
					(switch global126
						(1 770)
						(2 775)
						(3 785)
						(4 770)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance tut of Actor
	(properties
		x -60
		y 113
		view 753
	)
)

(instance reporterHeads of Prop
	(properties
		x 61
		y 73
		view 752
		loop 1
		signal $4000
	)
)

(instance paper of Prop
	(properties
		x 154
		y 96
		view 760
	)
)

(instance r_arm of Prop
	(properties
		x 255
		y 91
		view 752
		loop 5
	)
)

(instance l_arm of Prop
	(properties
		x 280
		y 90
		view 752
		loop 6
	)
)

(instance reporters of View
	(properties
		x 62
		y 72
		view 752
	)
)

(instance headline of View
	(properties
		x 33
		y 59
		view 760
	)
)

(instance Coroner of Talker
	(properties
		x 0
		y 0
		view 1750
		loop 3
		talkWidth 150
		back 15
		textX 10
		textY 10
	)
	
	(method (init)
		(= font userFont)
		(super init: coronerBust coronerEyes coronerMouth &rest)
	)
)

(instance coronerBust of Prop
	(properties
		view 1750
		loop 1
	)
)

(instance coronerEyes of Prop
	(properties
		nsTop 77
		nsLeft 263
		view 1750
		loop 2
	)
)

(instance coronerMouth of Prop
	(properties
		nsTop 80
		nsLeft 264
		view 1750
	)
)
