;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include game.sh)
(use Main)
(use AudioScript)
(use Sync)
(use Game)
(use Actor)
(use System)

(public
	rm700 0
)

(local
	thePic
	underbits
	underbits2
	underbits3
	underbits4
	theColor
	local6
)
(instance rm700 of Room
	
	(method (init)
		(= theColor (if isVGA 7 else vWHITE))
		(= thePic 2000)
		(self picture: thePic yourself:)
		(self setScript: openingCartoon)
		(super init: &rest)
	)
)

(instance openingCartoon of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				((ScriptID 763) doit:)
				(= cycles 1)
			)
			(2
				(curRoom setRegions: 769)
				(HandsOff)
				(Load PICTURE thePic (+ thePic 1) (+ thePic 2))
				(++ thePic)
				(syncIt init: setCycle: MouthSync 10101 hide:)
				(theAudio number: 10101 play:)
				(= waitForCue 4608)
			)
			(3
				(doDrawPic doit:)
				(= waitForCue 5376)
			)
			(4
				(doDrawPic doit:)
				(= waitForCue 5888)
			)
			(5
				(Load PICTURE (+ thePic 1) (+ thePic 2) (+ thePic 3))
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10102 hide:)
				(theAudio number: 10102 play: self)
				(self setScript: creditsScript)
			)
			(6
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10103 hide:)
				(theAudio number: 10103 play:)
				(= waitForCue 864)
			)
			(7
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10104 hide:)
				(theAudio number: 10104 play:)
				(= waitForCue 4608)
			)
			(8
				(Load PICTURE (+ thePic 1) (+ thePic 2) (+ thePic 3))
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10105 hide:)
				(theAudio number: 10105 play:)
				(= waitForCue 5380)
			)
			(9
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10106 hide:)
				(theAudio number: 10106 play:)
				(= waitForCue 5892)
			)
			(10
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10107 hide:)
				(theAudio number: 10107 play: self)
			)
			(11
				(Load
					PICTURE
					(+ thePic 1)
					(+ thePic 2)
					(+ thePic 3)
					(+ thePic 4)
					(+ thePic 5)
					(+ thePic 6)
					(+ thePic 7)
				)
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10108 hide:)
				(theAudio number: 10108 play:)
				(= waitForCue 9216)
			)
			(12
				(doDrawPic doit:)
				(= waitForCue 9472)
			)
			(13
				(doDrawPic doit:)
				(= waitForCue 9728)
			)
			(14
				(doDrawPic doit:)
				(= waitForCue 12288)
			)
			(15
				(doDrawPic doit:)
				(= waitForCue 13056)
			)
			(16
				(doDrawPic doit:)
				(= waitForCue 13316)
			)
			(17
				(doDrawPic doit:)
				(= waitForCue 13904)
			)
			(18
				(Load PICTURE (+ thePic 1) (+ thePic 2) (+ thePic 3))
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10109 hide:)
				(theAudio number: 10109 play:)
				(= waitForCue 16388)
			)
			(19
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10111 hide:)
				(theAudio number: 10111 play:)
				(= waitForCue 16724)
			)
			(20
				(doDrawPic doit:)
				(= waitForCue 17668)
			)
			(21
				(Load
					PICTURE
					(+ thePic 1)
					(+ thePic 2)
					(+ thePic 3)
					(+ thePic 4)
					(+ thePic 5)
				)
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10112 hide:)
				(theAudio number: 10112 play:)
				(= waitForCue 18180)
			)
			(22
				(doDrawPic doit:)
				(= waitForCue 18688)
			)
			(23
				(doDrawPic doit:)
				(= waitForCue 21504)
			)
			(24
				(doDrawPic doit:)
				(= waitForCue 22020)
			)
			(25
				(doDrawPic doit:)
				(= waitForCue 24576)
			)
			(26
				(Load
					PICTURE
					(+ thePic 1)
					(+ thePic 2)
					(+ thePic 3)
					(+ thePic 4)
					(+ thePic 5)
				)
				(doDrawPic doit:)
				(syncIt init: setCycle: MouthSync 10113 hide:)
				(theAudio number: 10113 play:)
				(= waitForCue 9216)
			)
			(27
				(doDrawPic doit:)
				(= waitForCue 9988)
			)
			(28
				(doDrawPic doit:)
				(= waitForCue 12288)
			)
			(29
				(doDrawPic doit:)
				(= waitForCue 14080)
			)
			(30
				(doDrawPic doit:)
				(= waitForCue 20992)
			)
			(31
				(HandsOn)
				(curRoom newRoom: 1)
			)
		)
	)
)

(instance creditsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= underbits4
					(Display 700 0
						p_at 70 10
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 1
						p_at 70 26
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 0
						p_at 69 9
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 1
						p_at 69 25
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 4)
			)
			(1
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits4)
				(Display 700 2 p_restore underbits3)
				(= cycles 1)
			)
			(2
				(= underbits4
					(Display 700 3
						p_at 11 75
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 4
						p_at 11 91
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 3
						p_at 10 74
						p_width 120
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 4
						p_at 10 90
						p_width 120
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 3)
			)
			(3
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits3)
				(Display 700 2 p_restore underbits4)
				(= cycles 1)
			)
			(4
				(= underbits4
					(Display 700 5
						p_at 70 10
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 6
						p_at 70 26
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 5
						p_at 69 9
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 6
						p_at 69 25
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 3)
			)
			(5
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits3)
				(Display 700 2 p_restore underbits4)
				(= cycles 1)
			)
			(6
				(= underbits4
					(Display 700 7
						p_at 121 24
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 8
						p_at 121 40
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 7
						p_at 120 23
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 8
						p_at 120 39
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 3)
			)
			(7
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits3)
				(Display 700 2 p_restore underbits4)
				(= cycles 1)
			)
			(8
				(= underbits4
					(Display 700 9
						p_at 21 11
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 10
						p_at 21 27
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 9
						p_at 20 10
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 10
						p_at 20 26
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 3)
			)
			(9
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits3)
				(Display 700 2 p_restore underbits4)
				(= cycles 1)
			)
			(10
				(= underbits4
					(Display 700 11
						p_at 11 75
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 700 12
						p_at 11 91
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 700 11
						p_at 10 74
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 700 12
						p_at 10 90
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 3)
			)
			(11
				(Display 700 2 p_restore underbits)
				(Display 700 2 p_restore underbits2)
				(Display 700 2 p_restore underbits3)
				(Display 700 2 p_restore underbits4)
				(self dispose:)
			)
		)
	)
)

(instance doDrawPic of Code

	(method (doit)
		(DrawPic thePic)
		(return (++ thePic))
	)
)

(instance syncIt of Prop
	
	(method (init)
		(if (not local6)
			(++ local6)
			(super init: &rest)
		)
	)
)
