;;; Sierra Script 1.0 - (do not remove this comment)
(script# 651)
(include game.sh)
(use Main)
(use Waters)
(use AudioScript)
(use LoadMany)
(use Motion)
(use Game)
(use System)

(public
	cdIntro2 0
)

(local
	underbits
	underbits2
	underbits3
	underbits4
	local4
	local5
	theColor
	saveSpeed
	theX = [40 154 80 185 169 80 267 189 80]
)
(instance cdIntro2 of Room
	(properties
		picture 69
	)
	
	(method (init)
		(Load PICTURE 69)
		(LoadMany FONT 600 8)
		(LoadMany VIEW 748 749)
		(Load SCRIPT 771)
		(super init:)
		(HandsOff)
		(theGame setCursor: invCursor TRUE)
		(= theColor (if isVGA 7 else vWHITE))
		(= saveSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 4)
		(ego
			normal: 1
			view: 748
			setStep: 3 2
			posn: -10 116
			setLoop: 3
			setPri: 1
			cycleSpeed: 2
			moveSpeed: 4
			init:
		)
		((ego head?)
			show:
			view: 748
			setLoop: 4
			moveHead: 2048
			signal: (| ignrHrz ((ego head?) signal?))
		)
		(self setScript: creditsScript setRegions: 769)
		(water init:)
		(Bset 22)
	)
	
	(method (dispose)
		(theGame egoMoveSpeed: saveSpeed)
		(DisposeScript 401)
		(super dispose:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(ego
			moveSpeed: 0
			setStep: 3 2
			setLoop: -1
			setCycle: KQ5SyncWalk
		)
	)
)

(instance sceneTwoScript of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(audioTrak init: hide: play: 10102 652)
				(= cycles 15)
			)
			(1
				(ego setMotion: MoveTo 154 112 self)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					cycleSpeed: 3
					x: (+ (ego x?) 4)
					setLoop: 5
					cel: 0
				)
				(= seconds 3)
			)
			(3 (ego setCycle: EndLoop self))
			(4 (= seconds 2))
			(5
				(ego setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(6 (= seconds 2))
			(7
				(ego setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(8
				(ego
					normal: TRUE
					setLoop: 6
					cycleSpeed: 2
					setStep: 2 2
					setCycle: SyncWalk
					setMotion: MoveTo 228 120 self
				)
				((ego head?) show: setLoop: 4 moveHead: 2048)
			)
			(9
				(ego setMotion: MoveTo 264 103 self)
			)
			(10
				(ego setMotion: MoveTo 292 89 self)
			)
			(11
				(ego setMotion: MoveTo 319 84 self)
			)
			(12
				(ego setMotion: MoveTo 323 84 self)
			)
			(13
				(if (or local5 (> (DoAudio Loc) -1))
					(-- state)
					(= cycles 1)
				else
					(curRoom newRoom: 652)
				)
			)
		)
	)
)

(instance creditsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: sceneTwoScript)
				(= local5 1)
				(= underbits4
					(Display 651 0
						p_at 70 10
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 1
						p_at 70 26
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 0
						p_at 69 9
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 1
						p_at 69 25
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 7)
			)
			(1
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits4)
				(Display 651 2 p_restore underbits3)
				(= cycles 1)
			)
			(2
				(= underbits4
					(Display 651 3
						p_at 11 75
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 4
						p_at 11 91
						p_width 120
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 3
						p_at 10 74
						p_width 120
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 4
						p_at 10 90
						p_width 120
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 6)
			)
			(3
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits3)
				(Display 651 2 p_restore underbits4)
				(= cycles 1)
			)
			(4
				(= underbits4
					(Display 651 5
						p_at 70 10
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 6
						p_at 70 26
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 5
						p_at 69 9
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 6
						p_at 69 25
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 6)
			)
			(5
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits3)
				(Display 651 2 p_restore underbits4)
				(= cycles 1)
			)
			(6
				(= underbits4
					(Display 651 7
						p_at 121 24
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 8
						p_at 121 40
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 7
						p_at 120 23
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 8
						p_at 120 39
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 5)
			)
			(7
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits3)
				(Display 651 2 p_restore underbits4)
				(= cycles 1)
			)
			(8
				(= underbits4
					(Display 651 9
						p_at 21 11
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 10
						p_at 21 27
						p_width 240
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 9
						p_at 20 10
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 10
						p_at 20 26
						p_width 240
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 5)
			)
			(9
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits3)
				(Display 651 2 p_restore underbits4)
				(= cycles 1)
			)
			(10
				(= underbits4
					(Display 651 11
						p_at 11 75
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 600
						p_save
					)
				)
				(= underbits3
					(Display 651 12
						p_at 11 91
						p_width 140
						p_mode teJustCenter
						p_color 0
						p_font 8
						p_save
					)
				)
				(= underbits2
					(Display 651 11
						p_at 10 74
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 600
						p_save
					)
				)
				(= underbits
					(Display 651 12
						p_at 10 90
						p_width 140
						p_mode teJustCenter
						p_color theColor
						p_font 8
						p_save
					)
				)
				(= seconds 5)
			)
			(11
				(Display 651 2 p_restore underbits)
				(Display 651 2 p_restore underbits2)
				(Display 651 2 p_restore underbits3)
				(Display 651 2 p_restore underbits4)
				(= local5 0)
			)
		)
	)
)

(instance water of Waters
	(properties
		view 749
		cycleSpeed 6
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
		(= cel [theX (++ pos)])
	)
	
	(method (saveLoop)
		(= [theX pos] cel)
		(++ pos)
	)
)

(instance audioTrak of MonoAudioProp)
