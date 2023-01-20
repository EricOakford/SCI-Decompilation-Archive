;;; Sierra Script 1.0 - (do not remove this comment)
(script# VIEWCART) ;706
(include game.sh)
(use Main)
(use DScript)
(use PolyPath)
(use Motion)
(use Actor)

(public
	viewCartridge 0
)

(local
	[str 251]
)
(instance screen of Prop
	(properties
		x 152
		y 135
		view 161
		loop 1
		cycleSpeed 6
	)
)

(instance viewCartridge of DScript
	(method (doit)
		(super doit:)
		(-- counter)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(UnLoad PICTURE 99)
		(DisposeScript VIEWCART)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic fade:)
				(= ticks 18)
			)
			(1
				(ego
					ignoreActors: TRUE
					setMotion: PolyPath 139 178 self
				)
			)
			(2
				(ego setHeading: 45 self)
			)
			(3
				(screen
					init:
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(cast eachElementDo: #hide)
				(cast eachElementDo: #stopUpd)
				((ScriptID DELTAUR 14) dispose:)
				((ScriptID DELTAUR 15) dispose:)
				(curRoom drawPic: 99 FADEOUT)
				(Display 706 0
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 3)
			)
			(5
				(curRoom drawPic: 99 SCROLLUP)
				(theMusic2 number: 413 loop: -1 play:)
				(Display 706 1
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 10)
			)
			(6
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 2
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 16)
			)
			(7
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 3
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 10)
			)
			(8
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 4
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 12)
			)
			(9
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 5
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color
					colLYellow
				)
				(= seconds 12)
			)
			(10
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 6
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 12)
			)
			(11
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 7
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 12)
			)
			(12
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 8
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 10)
			)
			(13
				(curRoom drawPic: 99 SCROLLUP)
				(SolvePuzzle 5 fLearnSelfDestructCode)
				(Format @str 706 9 selfDestructCode)
				(Display @str
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 20)
			)
			(14
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 10
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 16)
			)
			(15
				(curRoom drawPic: 99 SCROLLUP)
				(Display 706 11
					p_mode teJustCenter
					p_width 165
					p_at 78 40
					p_color colLYellow
				)
				(= seconds 4)
			)
			(16
				(curRoom drawPic: 99 SCROLLUP)
				(theMusic2 fade: loop: 0)
				(theMusic play: 0 fade: 127 25 10 0)
				(cast eachElementDo: #show)
				(cast eachElementDo: #startUpd)
				(ego ignoreActors: 0)
				((ScriptID DELTAUR 14) init: activate:)
				((ScriptID DELTAUR 15) init: activate:)
				((ego _head?) hide:)
				(curRoom drawPic: 61 FADEOUT)
				(screen setLoop: 2 cel: 0 cycleSpeed: 20 setCycle: Forward)
				(= seconds 3)
			)
			(17
				(screen dispose:)
				(= seconds 1)
			)
			(18
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if clrByKey
			(if (<= counter 0)
				(event claimed: TRUE)
				(= seconds 0)
				(= cycles 1)
			)
		else
			(super handleEvent: event)
		)
	)
)
