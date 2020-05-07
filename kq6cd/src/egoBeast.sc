;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGOBEAST) ;84
(include game.sh)
(use Main)
(use Kq6Sound)
(use KQ6Print)
(use Kq6Procs)
(use Print)
(use Window)
(use Motion)
(use Game)
(use System)

(public
	egoBeastScript 0
)

(instance egoBeastScript of Script
	(properties)
	
	(method (changeState newState &tmp printRet [str 200])
		(switch (= state newState)
			(0
				(theGame handsOff: killSound: 1)
				(theIconBar disable: 5 6)
				(localMusic2 loop: -1 number: 972 play:)
				(messager say: 3 0 7 1 self 0)
			)
			(1
				(messager say: 3 0 7 2 self 0)
			)
			(2
				(localMusic number: 974 play:)
				(ego
					view: 910
					normal: 0
					cycleSpeed: 10
					setLoop: 0
					setCycle: EndLoop self
				)
			)
			(3
				(localMusic number: 974 play:)
				(ego setCycle: BegLoop self)
			)
			(4
				(localMusic number: 974 play:)
				(ego setCycle: EndLoop self)
			)
			(5
				(messager say: 3 0 7 3 self 0)
			)
			(6
				(messager say: 3 0 7 4 self 0)
			)
			(7
				(localMusic2 number: 973 loop: 1 play:)
				(ego
					setLoop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(8
				(Bset 59)
				(KQ6Print
					say: 0 3 0 7 5 0 0 0
					posn: 10 30
					width: 289
					modeless: 1
					init:
				)
				(= seconds 4)
			)
			(9
				(if modelessDialog (modelessDialog dispose:))
				(localMusic2 stop:)
				(= cycles 1)
			)
			(10
				(Sounds eachElementDo: #stop)
				(localMusic flags: 1 number: 970 loop: 1 play:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98 10)
				(Message msgGET 916 0 0 2 1 @str)
				(Display @str
					p_at 30 11
					p_width 260
					p_color 16
					p_font userFont
					p_mode 1
				)
				(Display @str
					p_at 29 10
					p_width 260
					p_color 47
					p_font userFont
					p_mode 1
				)
				(ego
					view: 910
					loop: 1
					cel: (ego lastCel:)
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 151 97
					setMotion: 0
					show:
				)
				(= cycles 2)
			)
			(11
				(if (& msgType CD_MSG) (DoAudio Play 916 0 0 2 1))
				(theGame setCursor: normalCursor)
				(repeat
					(= printRet
						(Print
							window: deathWindow
							posn: 70 130
							addButton: 1 {Restore} 0 15
							addButton: 2 {Restart} 70 15
							addButton: 3 {Quit} 140 15
							init:
						)
					)
					(switch printRet
						(1
							(theGame restore:)
						)
						(2
							(theGame restart: TRUE)
						)
						(3
							(= quit TRUE)
							(break)
						)
					)
				)
			)
		)
	)
)

(instance deathWindow of SysWindow
	(properties)
	
	(method (open)
		(= color 47)
		(= back 0)
		(super open: &rest)
	)
)

(instance localMusic of Kq6Sound
	(properties)
)

(instance localMusic2 of Kq6Sound
	(properties)
)
