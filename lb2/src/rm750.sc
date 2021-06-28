;;; Sierra Script 1.0 - (do not remove this comment)
(script# 750)
(include game.sh) (include "750.shm")
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
	questionNum =  1
	pickedPerson
	pickedMotive
	local3
	correctAnswer =  TRUE
	correctAnswer2 =  TRUE
)
(procedure (PickPerson)
	(= pickedPerson
		(Print
			font: 69
			addText: N_TITLE NULL NULL 1 (WhichLanguage 0 0 42 0 0) 0
			addButton: 1 N_PERSON NULL NULL 1 0 17
			addButton: 2 N_PERSON NULL NULL 2 0 34
			addButton: 3 N_PERSON NULL NULL 3 112 34
			addButton: 4 N_PERSON NULL NULL 4 0 51
			addButton: 5 N_PERSON NULL NULL 5 112 51
			addButton: 6 N_PERSON NULL NULL 6 0 68
			addButton: 8 N_PERSON NULL NULL 8 112 68
			addButton: 7 N_PERSON NULL NULL 7 0 85
			addButton: 9 N_PERSON NULL NULL 9 0 102
			addButton: 10 N_PERSON NULL NULL 10 0 119
			addButton: 11 N_PERSON NULL NULL 11 0 136
			addButton: 12 N_PERSON NULL NULL 12 0 153
			init:
		)
	)
)

(procedure (PickMotive)
	(= pickedMotive
		(Print
			addText: N_TITLE NULL NULL 2 (WhichLanguage 0 0 67 0 0) 0
			addButton: 1 N_MOTIVE NULL NULL 1 0 17
			addButton: 2 N_MOTIVE NULL NULL 2 (WhichLanguage 128 80 128 80 96) 17
			addButton: 3 N_MOTIVE NULL NULL 3 0 34
			addButton: 4 N_MOTIVE NULL NULL 4 (WhichLanguage 80 80 128 80 80) 34
			addButton: 5 N_MOTIVE NULL NULL 5 0 51
			addButton: 6 N_MOTIVE NULL NULL 6 (WhichLanguage 80 80 128 80 80) 51
			addButton: 7 N_MOTIVE NULL NULL 7 0 68
			addButton: 8 N_MOTIVE NULL NULL 8 0 85
			addButton: 9 N_MOTIVE NULL NULL 9 0 102
			addButton: 10 N_MOTIVE NULL NULL 10 (WhichLanguage 0 0 8 0 0) 119
			addButton: 11 N_MOTIVE NULL NULL 11 0 136
			addButton: 12 N_MOTIVE NULL NULL 12 0 153
			addButton: 13 N_MOTIVE NULL NULL 13 (WhichLanguage 48 48 64 48 80) 153
			init:
		)
	)
)

(instance rm750 of LBRoom
	(properties
		picture 750
		style FADEOUT
	)
	
	(method (init)
		(LoadMany RES_PIC 760)
		(LoadMany RES_SOUND 760 120)
		(LoadMany RES_VIEW 1750 752 753 760)
		(Bset fEndGame)
		(super init:)
		(ego wearingGown: FALSE)
		(theIconBar disable:)
		(theMusic number: 120 flags: mNOPAUSE loop: -1 play:)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theGame setCursor: ARROW_CURSOR)
				(messager say: N_BEFORE_ASKING NULL NULL 0 self)
			)
			(2
				(= next sFirstFive)
				(self dispose:)
			)
		)
	)
)

(instance sFirstFive of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_QUESTION NULL NULL questionNum self)
			)
			(1
				(PickPerson)
				(if (!= pickedPerson 9)
					(= correctAnswer FALSE)
				)
				(PickMotive)
				(cond 
					((!= questionNum 4)
						(if (and (!= pickedMotive 9) (!= pickedMotive 10))
							(= correctAnswer FALSE)
						)
					)
					((and (!= pickedMotive 6) (!= pickedMotive 3))
						(= correctAnswer FALSE)
					)
				)
				(if (< (++ questionNum) 6)
					(self changeState: 0)
				else
					(= cycles 2)
				)
			)
			(2
				(if (Btst fEndGame)
					(= next sSixToNine)
				else
					(= correctAnswer FALSE)
					(= next sTenOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance sSixToNine of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= questionNum 6)
				(= cycles 1)
			)
			(1
				(messager say: N_QUESTION NULL NULL questionNum self)
			)
			(2
				(PickPerson)
				(switch questionNum
					(6
						(if (!= pickedPerson 1)
							(= correctAnswer 0)
							(self changeState: 4)
						else
							(++ questionNum)
						)
					)
					(9
						(if (!= pickedPerson 9) (= correctAnswer 0))
						(++ questionNum)
					)
					(else 
						(if (!= pickedPerson 6) (= correctAnswer 0))
						(++ questionNum)
					)
				)
				(if (< questionNum 10)
					(self changeState: 1)
				else
					(= cycles 1)
				)
			)
			(3
				(PickMotive)
				(if (and (!= pickedMotive 9) (!= pickedMotive 10))
					(= correctAnswer 0)
				)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= questionNum 10) (= cycles 1))
			(1
				(messager say: N_QUESTION NULL NULL questionNum self)
			)
			(2
				(PickPerson)
				(switch questionNum
					(10
						(if (!= pickedPerson 6)
							(= correctAnswer2 0)
							(= questionNum (+ questionNum 2))
						else
							(++ questionNum)
						)
						(= cycles 1)
					)
					(11
						(if (!= pickedPerson 9) (= correctAnswer2 0))
						(++ questionNum)
						(= cycles 1)
					)
					(12
						(if (!= pickedPerson 11)
							(= questionNum 15)
							(messager say: 8 NULL 6 0 self)
						else
							(++ questionNum)
							(messager say: 8 NULL 5 0 self)
						)
					)
					(13
						(if (!= pickedPerson 6)
							(= questionNum 15)
							(messager say: 8 0 6 0 self)
						else
							(++ questionNum)
							(messager say: 8 0 5 0 self)
						)
					)
					(14
						(if (!= pickedPerson 12)
							(messager say: 8 0 6 0 self)
						else
							(messager say: 8 0 5 0 self)
						)
						(++ questionNum)
					)
					(15
						(if (!= pickedPerson 8)
							(messager say: 9 NULL NULL 0 self)
						else
							(messager say: 9 0 7 0 self)
						)
						(++ questionNum)
					)
					(16
						(if (!= pickedPerson 5)
							(messager say: 10 NULL NULL 0 self)
						else
							(messager say: 10 0 8 0 self)
						)
						(++ questionNum)
					)
				)
			)
			(3
				(if (> questionNum 16)
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
							correctAnswer
							correctAnswer2
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
							correctAnswer
						)
						(= local3 1)
					)
					(correctAnswer2 (= local3 4))
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
							correctAnswer
							correctAnswer2
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
							correctAnswer
						)
						(= global126 4)
					)
					(correctAnswer2 (= global126 2))
					(else (= global126 3))
				)
				(if (or (== local3 1) (== local3 2))
					(tut init:)
					(messager say: 7 NULL NULL 0 self)
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
						(reporterHeads setCycle: EndLoop self)
					)
					((or (== global126 2) (== global126 4))
						(theMusic2 number: 751 flags: 1 loop: 1 play:)
						(reporterHeads setCycle: EndLoop self)
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
					(reporterHeads setLoop: 2 setCel: 0 setCycle: EndLoop self)
				)
			)
			(6
				(reporterHeads setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(7
				(reporterHeads setCel: 0 setLoop: 4 setCycle: Forward)
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
				(paper setCycle: EndLoop self)
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
