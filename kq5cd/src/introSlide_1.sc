;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include game.sh)
(use Main)
(use AudioScript)
(use Sync)
(use LoadMany)
(use Game)
(use Actor)
(use System)

(public
	introSlide_1 0
)

(local
	underbits
	underbits2
	underbits3
	underbits4
	textColor
)
(instance introSlide_1 of Room
	(properties
		picture 68
	)
	
	(method (init)
		(= textColor (if isVGA 7 else 15))
		(super init: &rest)
		(HandsOff)
		(theGame setCursor: invCursor TRUE)
		(= inCartoon TRUE)
		(Load PICTURE 55)
		(LoadMany VIEW 755 754)
		(Load FONT 8)
		(aCastle init:)
		(self setScript: openingCartoon)
	)
)

(instance openingCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 763) doit:)
				(= cycles 1)
			)
			(1
				(curRoom setRegions: 769)
				(= cycles 1)
			)
			(2
				(self setScript: scene1Script)
			)
			(3
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 68)
				(Load PICTURE 69)
				(LoadMany VIEW 748 749)
				(LoadMany FONT 600 8)
				(= seconds 2)
			)
			(4
				(self setScript: scene2Script)
			)
			(5
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 69)
				(Load PICTURE 68)
				(LoadMany VIEW 748 757 760)
				(= seconds 2)
			)
			(6
				(self setScript: scene3Script)
			)
			(7
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 68)
				(Load PICTURE 70)
				(LoadMany VIEW 761)
				(= seconds 2)
			)
			(8
				(self setScript: scene4Script)
			)
			(9
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 70)
				(Load PICTURE 71)
				(LoadMany VIEW 757 763)
				(= seconds 2)
			)
			(10
				(self setScript: scene5Script)
			)
			(11
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 71)
				(Load PICTURE 72)
				(LoadMany VIEW 761)
				(= seconds 2)
			)
			(12
				(self setScript: scene6Script)
			)
			(13
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 72)
				(Load PICTURE 70)
				(LoadMany VIEW 761)
				(= seconds 2)
			)
			(14
				(self setScript: scene7Script)
			)
			(15
				(DrawPic 55 FADEOUT)
				(cast eachElementDo: #hide)
				(cast eachElementDo: #dispose)
				(cast release:)
				(UnLoad PICTURE 70)
				(Load PICTURE 71)
				(LoadMany VIEW 763 757 758 759 785)
				(= seconds 2)
			)
			(16
				(self setScript: scene8Script)
			)
			(17 (= seconds 1))
			(18 (curRoom newRoom: 611))
		)
	)
)

(instance scene1Script of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(syncIt init: setCycle: MouthSync 10101 hide:)
				(theAudio number: 10101 play:)
				(= seconds 1)
			)
			(1
				(= underbits2
					(Display 610 0
						p_at 80 100
						p_font 8
						p_color 0
					)
				)
				(= underbits
					(Display 610 0
						p_at 79 99
						p_font 8
						p_color textColor
					)
				)
				(= seconds 3)
			)
			(2
				(DrawPic 68 PIXELDISSOLVE FALSE)
				(= cycles 1)
			)
			(3
				(= waitForCue 4608)
			)
			(4
				(Palette PALIntensity 0 255 0)
				(mordack init:)
				(= cycles 1)
			)
			(5
				(Palette PALIntensity 0 255 100)
				(= waitForCue 5120)
			)
			(6
				(Palette PALIntensity 0 255 0)
				(mordack loop: 2 cel: 4 show:)
				(lightning init:)
				(clouds init:)
				(= cycles 1)
			)
			(7
				(Palette PALIntensity 0 255 100)
				(= waitForCue 5200)
			)
			(8
				(Palette PALIntensity 0 255 0)
				(lightning loop: 5 cel: 2 show:)
				(clouds loop: 2 cel: 2 show:)
				(= cycles 1)
			)
			(9
				(Palette PALIntensity 0 255 100)
				(= waitForCue 5376)
			)
			(10
				(Palette PALIntensity 0 255 0)
				(lightning loop: 5 cel: 4 show:)
				(clouds cel: 3 show:)
				(= cycles 1)
			)
			(11
				(Palette PALIntensity 0 255 100)
				(= waitForCue 5632)
			)
			(12
				(Palette PALIntensity 0 255 0)
				(aCastle hide:)
				(lightning hide:)
				(mordack loop: 5 cel: 3 show:)
				(clouds loop: 3 cel: 7 show:)
				(= cycles 1)
			)
			(13
				(Palette PALIntensity 0 255 100)
				(= waitForCue 5888)
			)
			(14
				(Palette PALIntensity 0 255 0)
				(cast eachElementDo: #hide)
				(= cycles 1)
			)
			(15
				(Palette PALIntensity 0 255 100)
				(= seconds 3)
			)
			(16
				(if (!= (DoAudio Loc) -1) (-- state))
				(= cycles 1)
			)
			(17 (= seconds 1))
			(18
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene2Script of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 69 FADEOUT)
				(theEgo init:)
				(theHead init:)
				(water init:)
				(= underbits4
					(Display 610 1
						p_at 70 10
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 2
						p_at 70 26
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 1
						p_at 69 9
						p_width 140
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 2
						p_at 69 25
						p_width 140
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= seconds 2)
			)
			(1
				(theAudio number: 10102 play:)
				(= seconds 6)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(theEgo x: 146 y: 112 view: 748 show:)
				(theHead x: 146 y: 112 view: 748 show:)
				(water cel: 2 show:)
				(Display 610 3 p_restore underbits)
				(Display 610 3 p_restore underbits2)
				(Display 610 3 p_restore underbits4)
				(Display 610 3 p_restore underbits3)
				(= underbits4
					(Display 610 4
						p_at 11 75
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 5
						p_at 11 91
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 4
						p_at 10 74
						p_width 120
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 5
						p_at 10 90
						p_width 120
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= seconds 5)
			)
			(4
				(Palette PALIntensity 0 255 0)
				(theEgo x: 158 y: 112 loop: 5 cel: 6 show:)
				(theHead hide:)
				(water loop: 0 cel: 3 show:)
				(Display 610 3 p_restore underbits)
				(Display 610 3 p_restore underbits2)
				(Display 610 3 p_restore underbits3)
				(Display 610 3 p_restore underbits4)
				(= cycles 1)
			)
			(5
				(Palette PALIntensity 0 255 100)
				(= seconds 1)
			)
			(6
				(DrawPic 69 PIXELDISSOLVE)
				(theEgo loop: 7 cel: 1 show:)
				(water
					x: 185
					y: 169
					view: 749
					loop: 1
					cel: 5
					priority: 13
					show:
				)
				(= seconds 1)
			)
			(7
				(DrawPic 69 PIXELDISSOLVE)
				(theEgo loop: 7 cel: 5 show:)
				(water x: 185 y: 169 loop: 1 cel: 5 show:)
				(= underbits4
					(Display 610 6
						p_at 70 10
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 7
						p_at 70 26
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 6
						p_at 69 9
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 7
						p_at 69 25
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= seconds 6)
			)
			(8
				(Palette PALIntensity 0 255 0)
				(theEgo x: 222 y: 119 loop: 6 cel: 6 show:)
				(theHead x: 222 y: 119 loop: 4 cel: 0 priority: 8 show:)
				(water
					x: 40
					y: 154
					view: 749
					loop: 0
					cel: 3
					priority: 11
					show:
				)
				(Display 610 3 p_restore underbits)
				(Display 610 3 p_restore underbits2)
				(Display 610 3 p_restore underbits3)
				(Display 610 3 p_restore underbits4)
				(= underbits4
					(Display 610 8
						p_at 121 24
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 9
						p_at 121 40
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 8
						p_at 120 23
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 9
						p_at 120 39
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= cycles 1)
			)
			(9
				(Palette PALIntensity 0 255 100)
				(= seconds 6)
			)
			(10
				(Palette PALIntensity 0 255 0)
				(theEgo x: 290 y: 90 cel: 0 show:)
				(theHead x: 290 y: 90 priority: 5 show:)
				(water loop: 0 cel: 2 show:)
				(Display 610 3 p_restore underbits)
				(Display 610 3 p_restore underbits2)
				(Display 610 3 p_restore underbits3)
				(Display 610 3 p_restore underbits4)
				(= underbits4
					(Display 610 10
						p_at 21 11
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 11
						p_at 21 27
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 10
						p_at 20 10
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 11
						p_at 20 26
						p_width 240
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= cycles 1)
			)
			(11
				(Palette PALIntensity 0 255 100)
				(= seconds 6)
			)
			(12
				(Palette PALIntensity 0 255 0)
				(theEgo x: 323 y: 84 loop: 6 cel: 7 show:)
				(theHead
					x: 323
					y: 84
					z: 32
					loop: 4
					cel: 0
					priority: 4
					show:
				)
				(water loop: 0 cel: 2 show:)
				(Display 610 3 p_restore underbits)
				(Display 610 3 p_restore underbits2)
				(Display 610 3 p_restore underbits3)
				(Display 610 3 p_restore underbits4)
				(= underbits4
					(Display 610 12
						p_at 11 75
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 610 13
						p_at 11 91
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 610 12
						p_at 10 74
						p_width 140
						p_mode teJustCenter
						p_color textColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 610 13
						p_at 10 90
						p_width 140
						p_mode teJustCenter
						p_color textColor
						p_font 8
						p_save
					)
				)
				(= cycles 1)
			)
			(13
				(Palette PALIntensity 0 255 100)
				(= seconds 6)
			)
			(14
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(15
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene3Script of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 68 FADEOUT)
				(theEgo
					x: 44
					y: 162
					view: 748
					loop: 0
					cel: 6
					priority: 12
					init:
				)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10103 hide:)
				(theAudio number: 10103 play:)
				(= waitForCue 800)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(theEgo
					x: 44
					y: 162
					view: 748
					loop: 0
					cel: 6
					priority: 12
					show:
				)
				(owl
					x: 16
					y: 74
					view: 760
					loop: 2
					cel: 1
					priority: 6
					init:
				)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= seconds 1)
			)
			(4
				(DrawPic 68 PIXELDISSOLVE)
				(theEgo
					x: 44
					y: 162
					view: 748
					loop: 2
					cel: 8
					priority: 12
					show:
				)
				(owl
					x: 25
					y: 100
					view: 757
					loop: 0
					cel: 13
					priority: 6
					show:
				)
				(= cycles 1)
			)
			(5
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(6
				(= seconds 1)
			)
			(7
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene4Script of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 70 FADEOUT)
				(theMouth init:)
				(owlEyes init:)
				(egoEyes init:)
				(egoBody init:)
				(theEgo
					x: 121
					y: 78
					view: 761
					loop: 3
					cel: 0
					priority: 6
					init:
				)
				(leftLimb init:)
				(rightLimb init:)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10104 hide:)
				(theAudio number: 10104 play:)
				(= waitForCue 4356)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(owl
					x: 177
					y: 79
					z: 0
					view: 761
					loop: 0
					cel: 0
					priority: 4
					init:
				)
				(theEgo hide:)
				(theMouth
					x: 121
					y: 78
					z: 0
					view: 761
					loop: 3
					cel: 3
					priority: 6
				)
				(egoEyes loop: 2 cel: 0)
				(leftLimb loop: 5 cel: 3)
				(rightLimb loop: 6 cel: 0)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(5
				(= seconds 1)
			)
			(6
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene5Script of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 71 FADEOUT)
				(theHead
					x: 81
					y: 118
					z: 0
					view: 763
					loop: 0
					cel: 2
					priority: 10
					init:
				)
				(theEgo
					x: 102
					y: 109
					view: 763
					loop: 1
					cel: 0
					priority: 11
					init:
				)
				(owl
					x: 189
					y: 86
					view: 757
					loop: 1
					cel: 0
					priority: 10
					init:
				)
				(owlEyes
					x: 191
					y: 52
					view: 757
					loop: 2
					cel: 0
					priority: 11
					init:
				)
				(rightLimb
					x: 184
					y: 58
					view: 757
					loop: 4
					cel: 6
					priority: 12
					init:
				)
				(leftLimb
					x: 196
					y: 60
					z: 0
					view: 757
					loop: 5
					cel: 6
					priority: 12
					init:
				)
				(theMouth
					x: 192
					y: 56
					z: 0
					view: 757
					loop: 3
					cel: 2
					priority: 11
					init:
				)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10105 hide:)
				(theAudio number: 10105 play:)
				(= waitForCue 5124)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(theEgo hide:)
				(rightLimb loop: 4 cel: 0)
				(leftLimb loop: 6 cel: 4)
				(theMouth x: 102 y: 109 view: 763 loop: 1 cel: 4)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(5
				(= seconds 1)
			)
			(6
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene6Script of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 72 FADEOUT)
				(owlEyes
					x: 154
					y: 54
					view: 98
					loop: 0
					cel: 5
					priority: 10
					init:
				)
				(theMouth
					x: 152
					y: 75
					view: 1070
					loop: 1
					cel: 2
					priority: 10
					init:
				)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10106 hide:)
				(theAudio number: 10106 play:)
				(= waitForCue 5892)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(theMouth cel: 4)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(5
				(= seconds 2)
			)
			(6
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene7Script of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 70 FADEOUT)
				(owl
					x: 177
					y: 79
					view: 761
					loop: 0
					cel: 0
					priority: 4
					init:
				)
				(theMouth
					x: 121
					y: 78
					view: 761
					loop: 3
					cel: 1
					priority: 6
					init:
				)
				(owlEyes
					x: 175
					y: 66
					view: 761
					loop: 1
					cel: 0
					priority: 7
					init:
				)
				(egoEyes
					x: 121
					y: 78
					view: 761
					loop: 2
					cel: 0
					priority: 10
					init:
				)
				(egoBody init:)
				(rightLimb
					x: 102
					y: 84
					view: 761
					loop: 6
					cel: 0
					priority: 11
					init:
				)
				(leftLimb
					x: 128
					y: 75
					view: 761
					loop: 5
					cel: 3
					priority: 11
					init:
				)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10107 hide:)
				(theAudio number: 10107 play:)
				(= waitForCue 6404)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(owl hide:)
				(theMouth
					x: 177
					y: 79
					view: 761
					loop: 0
					cel: 0
					priority: 4
				)
				(theEgo
					x: 121
					y: 78
					view: 761
					loop: 3
					cel: 0
					priority: 6
					init:
				)
				(egoEyes loop: 2 cel: 0)
				(leftLimb loop: 5 cel: 0)
				(rightLimb loop: 6 cel: 0)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(5
				(= seconds 1)
			)
			(6
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scene8Script of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 71 FADEOUT)
				(theEgo
					x: 81
					y: 118
					view: 763
					loop: 0
					cel: 2
					priority: 10
					init:
				)
				(egoBody
					x: 102
					y: 109
					view: 763
					loop: 1
					cel: 0
					priority: 11
					init:
				)
				(owl
					x: 189
					y: 86
					view: 757
					loop: 1
					cel: 0
					priority: 10
					init:
				)
				(owlEyes
					x: 191
					y: 52
					view: 757
					loop: 2
					cel: 0
					priority: 11
					init:
				)
				(rightLimb
					x: 184
					y: 58
					view: 757
					loop: 4
					cel: 0
					priority: 12
					init:
				)
				(leftLimb
					x: 196
					y: 60
					view: 757
					loop: 5
					cel: 6
					priority: 12
					init:
				)
				(theMouth
					x: 192
					y: 56
					view: 757
					loop: 3
					cel: 7
					priority: 11
					init:
				)
				(= seconds 2)
			)
			(1
				(syncIt init: setCycle: MouthSync 10108 hide:)
				(theAudio number: 10108 play:)
				(= waitForCue 9216)
			)
			(2
				(Palette PALIntensity 0 255 0)
				(owl view: 758 loop: 2 cel: 9 priority: 1)
				(owlEyes hide:)
				(rightLimb hide:)
				(leftLimb hide:)
				(theMouth hide:)
				(= cycles 1)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= waitForCue 9296)
			)
			(4
				(Palette PALIntensity 0 255 0)
				(theEgo
					x: 101
					y: 105
					view: 763
					loop: 0
					cel: 0
					priority: 10
				)
				(egoBody hide:)
				(owl x: 203 y: 130 view: 758 loop: 3 cel: 4 priority: 1)
				(= cycles 1)
			)
			(5
				(Palette PALIntensity 0 255 100)
				(= seconds 2)
			)
			(6
				(Palette PALIntensity 0 255 0)
				(owl x: 203 y: 180 view: 758 loop: 3 cel: 2)
				(= cycles 1)
			)
			(7
				(Palette PALIntensity 0 255 100)
				(= waitForCue 9472)
			)
			(8
				(Palette PALIntensity 0 255 0)
				(owl x: 203 y: 242 view: 758 loop: 3 cel: 0)
				(leaves
					x: 197
					y: 188
					view: 758
					loop: 4
					cel: 11
					priority: 14
					init:
				)
				(= cycles 1)
			)
			(9
				(Palette PALIntensity 0 255 100)
				(= seconds 2)
			)
			(10
				(DrawPic 71 PIXELDISSOLVE)
				(owl x: 192 y: 168 view: 759 loop: 0 cel: 0 priority: 0)
				(leaves hide:)
				(= cycles 10)
			)
			(11
				(DrawPic 71 PIXELDISSOLVE)
				(theEgo x: 81 y: 118 view: 763 loop: 0 cel: 2)
				(egoBody
					x: 102
					y: 109
					view: 763
					loop: 1
					cel: 0
					priority: 11
					show:
				)
				(owl x: 189 y: 86 view: 757 loop: 1 cel: 0 priority: 10)
				(owlEyes
					x: 191
					y: 52
					view: 757
					loop: 2
					cel: 0
					priority: 11
					show:
				)
				(rightLimb
					x: 184
					y: 58
					view: 757
					loop: 4
					cel: 0
					priority: 12
					show:
				)
				(leftLimb
					x: 196
					y: 60
					view: 757
					loop: 6
					cel: 4
					priority: 12
				)
				(theMouth
					x: 192
					y: 56
					view: 757
					loop: 3
					cel: 7
					priority: 11
					show:
				)
				(= waitForCue 10240)
			)
			(12
				(Palette PALIntensity 0 255 0)
				(egoBody hide:)
				(theMouth
					x: 102
					y: 109
					view: 763
					loop: 1
					cel: 3
					priority: 11
				)
				(= cycles 1)
			)
			(13
				(Palette PALIntensity 0 255 100)
				(= waitForCue 10496)
			)
			(14
				(Palette PALIntensity 0 255 0)
				(egoBody
					x: 102
					y: 109
					view: 763
					loop: 1
					cel: 0
					priority: 11
					show:
				)
				(theMouth
					x: 192
					y: 56
					view: 757
					loop: 3
					cel: 7
					priority: 11
				)
				(= cycles 1)
			)
			(15
				(Palette PALIntensity 0 255 100)
				(= waitForCue 10576)
			)
			(16
				(Palette PALIntensity 0 255 0)
				(owl x: 189 y: 86 view: 759 loop: 2 cel: 8 priority: 10)
				(owlEyes hide:)
				(rightLimb hide:)
				(leftLimb hide:)
				(theMouth hide:)
				(= cycles 1)
			)
			(17
				(Palette PALIntensity 0 255 100)
				(= waitForCue 10608)
			)
			(18
				(Palette PALIntensity 0 255 0)
				(owl x: 189 y: 86 view: 759 loop: 3 cel: 6 priority: 10)
				(theMouth
					x: 192
					y: 56
					view: 757
					loop: 3
					cel: 7
					priority: 11
					show:
				)
				(= cycles 1)
			)
			(19
				(Palette PALIntensity 0 255 100)
				(= waitForCue 13056)
			)
			(20
				(Palette PALIntensity 0 255 0)
				(owl x: 180 y: 83 view: 785 loop: 5 cel: 0 priority: 10)
				(theMouth hide:)
				(= cycles 1)
			)
			(21
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(22
				(DrawPic 71 PIXELDISSOLVE)
				(theEgo
					x: 97
					y: 109
					view: 763
					loop: 0
					cel: 4
					priority: 10
					show:
				)
				(egoBody hide:)
				(owl x: 110 y: 48 view: 785 loop: 6 cel: 5 priority: 10)
				(= seconds 2)
			)
			(23
				(DrawPic 71 WIPEDOWN)
				(owl x: 110 y: 48 view: 785 loop: 6 cel: 4 priority: 10)
				(dust init:)
				(= waitForCue 13568)
			)
			(24
				(Palette PALIntensity 0 255 0)
				(owl x: 110 y: 48 view: 785 loop: 10 cel: 2 priority: 10)
				(dust hide:)
				(theMouth
					x: 95
					y: 24
					view: 757
					loop: 3
					cel: 6
					priority: 11
					show:
				)
				(sack init:)
				(= cycles 1)
			)
			(25
				(Palette PALIntensity 0 255 100)
				(= waitForCue 13904)
			)
			(26
				(DrawPic 71 PIXELDISSOLVE)
				(theEgo
					x: 103
					y: 108
					view: 763
					loop: 0
					cel: 1
					priority: 10
				)
				(owl x: 55 y: 31 view: 785 loop: 10 cel: 1 priority: 10)
				(theMouth hide:)
				(= cycles 1)
			)
			(27
				(if (!= (DoAudio Loc) -1)
					(-- state)
				)
				(= cycles 1)
			)
			(28
				(= seconds 1)
			)
			(29
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance aCastle of View
	(properties
		x 157
		y 77
		view 755
		loop 4
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance mordack of View
	(properties
		x 230
		y 176
		view 754
		cel 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance lightning of View
	(properties
		x 157
		y 77
		view 755
		loop 5
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance clouds of View
	(properties
		x 157
		y 86
		view 755
		loop 1
		cel 3
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance theEgo of View
	(properties
		x 11
		y 115
		view 748
		loop 3
		cel 8
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance theHead of View
	(properties
		x 11
		y 115
		z 31
		view 748
		loop 4
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance water of View
	(properties
		x 40
		y 154
		view 749
		cel 1
		priority 11
		signal (| ignrAct fixPriOn)
	)
)

(instance owl of View
	(properties
		x 25
		y 100
		view 757
		cel 13
		priority 6
		signal (| ignrAct fixPriOn)
	)
)

(instance owlEyes of View
	(properties
		x 175
		y 66
		view 761
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance theMouth of View
	(properties
		x 177
		y 79
		view 761
		cel 3
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance egoEyes of View
	(properties
		x 121
		y 78
		view 761
		loop 2
		cel 1
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance leftLimb of View
	(properties
		x 128
		y 75
		view 761
		loop 5
		priority 11
		signal (| ignrAct fixPriOn)
	)
)

(instance rightLimb of View
	(properties
		x 102
		y 84
		view 761
		loop 6
		priority 11
		signal (| ignrAct fixPriOn)
	)
)

(instance egoBody of View
	(properties
		x 132
		y 128
		view 761
		loop 4
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance leaves of View
	(properties
		x 197
		y 188
		view 758
		loop 4
		cel 11
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance sack of View
	(properties
		x 59
		y 201
		view 785
		loop 9
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance dust of View
	(properties
		x 87
		y 54
		view 785
		loop 7
		cel 5
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance syncIt of Prop)
