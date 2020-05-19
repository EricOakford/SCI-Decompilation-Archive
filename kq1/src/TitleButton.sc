;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include game.sh)
(use Main)
(use Intrface)
(use User)
(use Actor)
(use System)

(public
	TitleButton 0
)

(local
	[local0 4] = [58 161 58 161]
	[local4 4] = [146 146 165 165]
	buttonHighlighted
	local9
	buttonPressed
)

(enum
	buttonINTRO
	buttonNEWGAME
	buttonCREDITS
	buttonRESTORE
)

(procedure (PressButton param1 param2)
	(= param1 buttonHighlighted)
	(highLight x: [local0 buttonHighlighted] y: [local4 param1])
	(if (== argc 2)
		(= buttonHighlighted (= buttonPressed param2))
		(highLight x: [local0 buttonPressed] y: [local4 buttonHighlighted])
		(TitleButton changeState: 1)
	)
)

(procedure (ShowButtons)
	(RedrawCast)
	(Display 87 1 p_at 76 149 p_font 4 p_color vYELLOW)
	(Display 87 2 p_at 179 149 p_font 4 p_color vYELLOW)
	(Display 87 3 p_at 84 168 p_font 4 p_color vYELLOW)
	(Display 87 4 p_at 173 168 p_font 4 p_color vYELLOW)
)

(instance TitleButton of Script
	(properties)
	
	(method (init)
		(Animate (cast elements?) TRUE)
		(super init: &rest)
		(= global108 1)
		(User mapKeyToDir: FALSE)
		(addToPics add: b0 b1 b2 b3 eachElementDo: #init doit:)
		(= buttonHighlighted buttonINTRO)
		(highLight init: x: [local0 buttonHighlighted] y: [local4 buttonHighlighted])
		(ShowButtons)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (!= local9 buttonHighlighted)
			(= buttonHighlighted (= local9 buttonHighlighted))
			(PressButton buttonHighlighted)
		)
		(if (!= theCursor normalCursor)
			(= theCursor normalCursor)
			(SetCursor normalCursor (HaveMouse))
		)
	)
	
	(method (dispose)
		(DisposeScript 87)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1 (= cycles 2))
			(2
				(if (== buttonHighlighted buttonRESTORE)
					(self state: 0)
					(User mapKeyToDir: TRUE)
					(theGame restore:)
					(User mapKeyToDir: FALSE)
				else
					((ScriptID 86 1) changeState: 3)
				)
			)
			(3
				(User mapKeyToDir: TRUE)
				(= global108 0)
				(switch buttonPressed
					(buttonINTRO (curRoom newRoom: 200))
					(buttonNEWGAME (curRoom newRoom: 1))
					(buttonCREDITS (curRoom newRoom: 84))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(RIGHTARROW
						(if (or (== buttonHighlighted buttonINTRO) (== buttonHighlighted buttonCREDITS))
							(++ buttonHighlighted)
						else
							(-- buttonHighlighted)
						)
					)
					(LEFTARROW
						(if (or (== buttonHighlighted buttonNEWGAME) (== buttonHighlighted buttonRESTORE))
							(-- buttonHighlighted)
						else
							(++ buttonHighlighted)
						)
					)
					(UPARROW
						(if (<= buttonHighlighted buttonNEWGAME)
							(= buttonHighlighted (+ buttonHighlighted buttonCREDITS))
						else
							(= buttonHighlighted (- buttonHighlighted buttonCREDITS))
						)
					)
					(DOWNARROW
						(if (>= buttonHighlighted buttonCREDITS)
							(= buttonHighlighted (- buttonHighlighted buttonCREDITS))
						else
							(= buttonHighlighted (+ buttonHighlighted buttonCREDITS))
						)
					)
					(TAB
						(if (== buttonHighlighted buttonRESTORE)
							(= buttonHighlighted buttonINTRO)
						else
							(= buttonHighlighted (+ buttonHighlighted buttonNEWGAME))
						)
					)
					(SHIFTTAB
						(if (== buttonHighlighted buttonINTRO)
							(= buttonHighlighted buttonRESTORE)
						else
							(= buttonHighlighted (- buttonHighlighted buttonNEWGAME))
						)
					)
					(ENTER
						(PressButton buttonHighlighted buttonHighlighted)
					)
					(`#2
						(super handleEvent: event)
					)
					(else  (Print 87 0))
				)
			)
			(mouseDown
				(cond 
					((MousedOn b0 event) (PressButton 0 0))
					((MousedOn b1 event) (PressButton 1 1))
					((MousedOn b2 event) (PressButton 2 2))
					((MousedOn b3 event) (PressButton 3 3))
					(else (Print 87 0))
				)
			)
		)
	)
)

(instance b0 of View
	(properties
		x 58
		y 146
		view 950
		loop 1
	)
)

(instance b1 of View
	(properties
		x 161
		y 146
		view 950
		loop 1
	)
)

(instance b2 of View
	(properties
		x 58
		y 165
		view 950
		loop 1
	)
)

(instance b3 of View
	(properties
		x 161
		y 165
		view 950
		loop 1
	)
)

(instance highLight of View
	(properties
		view 950
		loop 2
		signal (| ignrAct fixedCel fixedLoop)
	)
)
