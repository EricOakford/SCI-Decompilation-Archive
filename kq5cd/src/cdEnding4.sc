;;; Sierra Script 1.0 - (do not remove this comment)
(script# 673)
(include game.sh)
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
	theFont
	foreColor
	backColor
	local11
)
(procedure (RedrawPic)
	(DrawPic 68 PIXELDISSOLVE FALSE)
	(theEnd forceUpd:)
	(addToPics add: aCastle doit:)
)

(instance cdEnding4 of KQ5Room
	(properties
		picture 68
	)
	
	(method (init)
		(super init:)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(Load VIEW 936)
		(LoadMany FONT 699 799)
		(theGame setCursor: normalCursor TRUE)
		(addToPics add: aCastle doit:)
		(self setScript: cartoon)
	)
	
	(method (doit)
		(super doit:)
		(theGame setCursor: normalCursor TRUE)
		(if isVGA
			(= foreColor 7)
			(= backColor 0)
			(= theFont USERFONT)
		else
			(= foreColor vWHITE)
			(= backColor vBLACK)
			(= theFont SYSFONT)
		)
		(if (and (== (theMusic prevSignal?) -1) local11)
			(if (< (DoSound NumVoices) 32)
				(theMusic number: 699 loop: -1 playBed:)
			else
				(theMusic number: 799 loop: -1 playBed:)
			)
		)
	)
)

(instance cartoon of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(syncIt init: hide: play: 10126)
				(= waitForCue 14592)
			)
			(2
				(theIconBar disable:
					ICON_WALK
					ICON_LOOK
					ICON_DO
					ICON_TALK
					ICON_ITEM
					ICON_INVENTORY
				)
				(family init: setCycle: EndLoop self)
			)
			(3
				(family cel: 0 setLoop: 9 setCycle: EndLoop self)
			)
			(4 (= waitForCue 14848))
			(5
				(egoHead init:)
				(valHead init:)
				(= waitForCue 15104)
			)
			(6
				(family setLoop: 2 setCycle: EndLoop self)
			)
			(7
				(egoHead setCycle: EndLoop self)
			)
			(8
				(cls)
				(= waitForCue 15360)
			)
			(9
				(cls)
				(egoHead setCycle: BegLoop)
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
					setCycle: Forward
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
				(if (> (DoAudio Loc) -1) (-- state))
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
		signal ignrAct
	)
)

(instance family of Actor
	(properties
		x 151
		y 183
		view 934
		loop 1
		priority 14
		signal fixPriOn
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
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
	)
)

(instance creditsScript of Script
	
	(method (changeState newState &tmp [str 60])
		(switch (= state newState)
			(0
				(Display 673 0
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 0
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display
					(Format @str 673 1 score possibleScore)
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display
					(Format @str 673 1 score possibleScore)
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(1
				(RedrawPic)
				(Display 673 2
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 2
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 3
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 3
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 4)
			)
			(2
				(RedrawPic)
				(Display 673 4
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 4
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 5
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 5
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 6
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 6
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 12)
			)
			(3
				(RedrawPic)
				(Display 673 4
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 4
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 7
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 7
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 8
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 8
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 8)
			)
			(4
				(RedrawPic)
				(Display 673 9
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 9
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 10
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 10
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(5
				(RedrawPic)
				(Display 673 11
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 11
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 12
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 12
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 4)
			)
			(6
				(RedrawPic)
				(Display 673 13
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 13
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 14
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 14
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 15
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 15
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 12)
			)
			(7
				(RedrawPic)
				(Display 673 16
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 16
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 17
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 17
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 4)
			)
			(8
				(RedrawPic)
				(theIconBar enable:)
				(theIconBar disable:
					ICON_WALK ICON_LOOK ICON_DO ICON_TALK
				)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 19
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 19
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(9
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 20
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 20
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(10
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 21
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 21
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(11
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 22
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 22
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(12
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 23
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 23
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(13
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 24
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 24
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 10)
			)
			(14
				(RedrawPic)
				(Display 673 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 25
					p_at 90 80
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 25
					p_at 89 79
					p_width SCRNWIDE
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 5)
			)
			(15
				(RedrawPic)
				(Display 673 26
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 26
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 27
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 27
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 5)
			)
			(16
				(RedrawPic)
				(Display 673 28
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 28
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 673 29
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 673 29
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(= seconds 5)
			)
			(17
				(RedrawPic)
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

(instance syncIt of MonoAudioProp)
