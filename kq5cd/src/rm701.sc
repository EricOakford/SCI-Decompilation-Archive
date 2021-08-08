;;; Sierra Script 1.0 - (do not remove this comment)
(script# 701)
(include game.sh)
(use Main)
(use AudioScript)
(use Sync)
(use Game)
(use Actor)
(use System)

(public
	rm701 0
)

(local
	thePic
	local1
	[local2 8]
	theFont
	backColor
	foreColor
)
(procedure (RedrawPic)
	(DrawPic thePic PIXELDISSOLVE FALSE)
)

(instance rm701 of Room
	
	(method (init)
		(= thePic 2050)
		(self
			picture: thePic
			yourself:
			setScript: closingCartoon
		)
		(if isVGA
			(= backColor 7)
			(= foreColor 0)
			(= theFont USERFONT)
		else
			(= backColor vWHITE)
			(= foreColor vBLACK)
			(= theFont SYSFONT)
		)
		(super init: &rest)
	)
)

(instance closingCartoon of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				((ScriptID 763) doit:)
				(= cycles 1)
			)
			(2
				(HandsOff)
				(++ thePic)
				(syncIt init: setCycle: MouthSync 10121 hide:)
				(theAudio number: 10121 play:)
				(= waitForCue 8960)
			)
			(3
				(doDrawPic doit:)
				(= waitForCue 10752)
			)
			(4
				(doDrawPic doit:)
				(= waitForCue 11264)
			)
			(5
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10122 hide:)
				(theAudio number: 10122 play:)
				(= waitForCue 12804)
			)
			(6
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10123 hide:)
				(theAudio number: 10123 play:)
				(= waitForCue 15360)
			)
			(7
				(doDrawPic doit:)
				(= waitForCue 15872)
			)
			(8
				(doDrawPic doit:)
				(= waitForCue 16896)
			)
			(9
				(doDrawPic doit:)
				(theAudio number: 10124 play: self)
			)
			(10
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10125 hide:)
				(theAudio number: 10125 play:)
				(= waitForCue 5888)
			)
			(11
				(doDrawPic doit:)
				(= waitForCue 6656)
			)
			(12
				(doDrawPic doit:)
				(= waitForCue 10496)
			)
			(13
				(doDrawPic doit:)
				(= waitForCue 12800)
			)
			(14
				(doDrawPic doit:)
				(= cycles 1)
			)
			(15
				(doDrawPic doit:)
				(= waitForCue 14336)
			)
			(16
				(doDrawPic doit:)
				(= cycles 1)
			)
			(17
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10126 hide:)
				(theAudio number: 10126 play:)
				(= waitForCue 15104)
			)
			(18
				(doDrawPic doit:)
				(= waitForCue 15616)
			)
			(19
				(doDrawPic doit:)
				(= thePic 2067)
				(curRoom setScript: creditsScript)
			)
		)
	)
)

(instance creditsScript of Script
	
	(method (changeState newState &tmp [str 60])
		(switch (= state newState)
			(0
				(Display 701 0
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 0
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display
					(Format @str 701 1 score possibleScore)
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display
					(Format @str 701 1 score possibleScore)
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(1
				(RedrawPic)
				(Display 701 2
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 2
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 3
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 3
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 4)
			)
			(2
				(RedrawPic)
				(Display 701 4
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 4
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 5
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 5
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 6
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 6
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 12)
			)
			(3
				(RedrawPic)
				(Display 701 4
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 4
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 7
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 7
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 8
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 8
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 8)
			)
			(4
				(RedrawPic)
				(Display 701 9
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 9
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 10
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 10
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(5
				(RedrawPic)
				(Display 701 11
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 11
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 12
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 12
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 4)
			)
			(6
				(RedrawPic)
				(Display 701 13
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 13
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 14
					p_at 35 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 14
					p_at 34 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 15
					p_at 145 80
					p_width 130
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 15
					p_at 144 79
					p_width 130
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 12)
			)
			(7
				(RedrawPic)
				(Display 701 16
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 16
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 17
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 17
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 4)
			)
			(8
				(RedrawPic)
				(theIconBar enable:)
				(theIconBar disable: ICON_WALK ICON_LOOK ICON_DO ICON_TALK)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 19
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 19
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(9
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 20
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 20
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(10
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 21
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 21
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(11
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 22
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 22
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(12
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 23
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 23
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(13
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 24
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 24
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 10)
			)
			(14
				(RedrawPic)
				(Display 701 18
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 18
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 25
					p_at 90 80
					p_width 320
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 25
					p_at 89 79
					p_width 320
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 5)
			)
			(15
				(RedrawPic)
				(Display 701 26
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 26
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 27
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 27
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(= seconds 5)
			)
			(16
				(RedrawPic)
				(Display 701 28
					p_at 90 60
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 28
					p_at 89 59
					p_width 140
					p_mode teJustCenter
					p_color backColor
					p_font theFont
				)
				(Display 701 29
					p_at 90 80
					p_width 140
					p_mode teJustCenter
					p_color foreColor
					p_font theFont
				)
				(Display 701 29
					p_at 89 79
					p_width 140
					p_mode teJustCenter
					p_color backColor
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

(instance syncIt of Prop

	(method (init)
		(if (not local1)
			(++ local1)
			(super init: &rest)
		)
	)
)

(instance doDrawPic of Code

	(method (doit)
		(DrawPic thePic)
		(return (++ thePic))
	)
)
