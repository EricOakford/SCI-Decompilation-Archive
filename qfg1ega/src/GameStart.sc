;;; Sierra Script 1.0 - (do not remove this comment)
(script# NOTICE2)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	notice2Room 0
)

(enum 1
	BUTTON_INTRO
	BUTTON_NEWGAME
	BUTTON_RESTORE
)

(local
	buttonIntro
	buttonNewGame
	buttonRestore
	buttonHighlight
	selectedButton
)
(procedure (HighlightSelectedButton)
	(switch selectedButton
		(BUTTON_INTRO
			(buttonHighlight posn: 249 90 forceUpd:)
		)
		(BUTTON_NEWGAME
			(buttonHighlight posn: 249 119 forceUpd:)
		)
		(BUTTON_RESTORE
			(buttonHighlight posn: 249 153 forceUpd:)
		)
	)
)

(procedure (ClickButton &tmp nextRoom)
	(switch selectedButton
		(BUTTON_INTRO
			(= nextRoom 200)	;intro room
		)
		(BUTTON_NEWGAME
			(= nextRoom 202)	;select character screen
		)
		(BUTTON_RESTORE
			(theGame restore:)	;open Restore dialog
			(return)
		)
	)
	(curRoom newRoom: nextRoom)
)

(instance wanted of PicView
	(properties
		y 59
		x 109
		view vGameSelect
	)
)

(instance forThe of PicView
	(properties
		y 74
		x 108
		view vGameSelect
		cel 1
	)
)

(instance village of PicView
	(properties
		y 93
		x 109
		view vGameSelect
		cel 2
	)
)

(instance emblem of PicView
	(properties
		y 163
		x 106
		view vGameSelect
		loop 1
	)
)

(instance nail1 of PicView
	(properties
		y 165
		x 47
		view vGameSelect
		loop 1
		cel 1
	)
)

(instance nail2 of PicView
	(properties
		y 47
		x 46
		view vGameSelect
		loop 1
		cel 2
	)
)

(instance nail3 of PicView
	(properties
		y 48
		x 170
		view vGameSelect
		loop 1
		cel 1
	)
)

(instance scorp of Actor
	(properties
		y 200
		x 80
		view vGameSelect
	)
)

(instance notice2Room of Room
	(properties
		picture 100
		style VSHUTTER
	)
	
	(method (init)
		(super init: &rest)
		(Load VIEW vGameSelect)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(directionHandler add: self)
		(addToPics
			add: wanted forThe village emblem nail1 nail2 nail3
			eachElementDo: #init
			doit:
		)
		((= buttonIntro (View new:))
			view: vGameSelect
			loop: 2
			cel: 0
			posn: 249 87
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= buttonNewGame (View new:))
			view: vGameSelect
			loop: 2
			cel: 0
			posn: 249 116
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= buttonRestore (View new:))
			view: vGameSelect
			loop: 2
			cel: 2
			posn: 249 150
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		(RedrawCast)
		(Display {Introduction}
			p_at 213 76
			p_mode teJustLeft
			p_font 300
			p_color vBROWN
		)
		(Display {Start New Hero}
			p_at 205 105
			p_mode teJustLeft
			p_font 300
			p_color vBROWN
		)
		(Display {Continue Quest}
			p_at 206 139
			p_mode teJustLeft
			p_font 300
			p_color vBROWN
		)
		(Display {Introduction}
			p_at 212 76
			p_mode teJustLeft
			p_font 300
			p_color vBLACK
		)
		(Display {Start New Hero}
			p_at 204 105
			p_mode teJustLeft
			p_font 300
			p_color vBLACK
		)
		(Display {Continue Quest}
			p_at 205 139
			p_mode teJustLeft
			p_font 300
			p_color vBLACK
		)
		(= selectedButton 1)
		((= buttonHighlight (View new:))
			view: vGameSelect
			setLoop: 2
			setCel: 3
			posn: 249 90
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
		)
		(scorp
			illegalBits: 0
			ignoreActors: 1
			init:
			setLoop: 3
			setCycle: Forward
			setStep: 1 1
			setPri: 13
			setScript: scorpS
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(User canControl: TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 theButton)
		(if
			(or
				(Said 'look/bug,scorpion')
				(MouseClaimed scorp event shiftDown)
			)
			(HighPrint 9 0)
			;Congratulations! You have discovered the first bug in this game.
		)
		(switch (event type?)
			(mouseDown
				(= theButton 0)
				(switch (= temp0 (OnControl CMAP (event x?) (event y?)))
					(cBLUE (= theButton BUTTON_INTRO))
					(cGREEN (= theButton BUTTON_NEWGAME))
					(cCYAN (= theButton BUTTON_RESTORE))
				)
				(event claimed: TRUE)
				(HighlightSelectedButton)
				(if theButton
					(= selectedButton theButton)
					(HighlightSelectedButton)
					(RedrawCast)
					(ClickButton)
				)
			)
			(direction
				(switch (event message?)
					(dirN
						(if (== selectedButton BUTTON_INTRO)
							(= selectedButton BUTTON_RESTORE)
						else
							(-- selectedButton)
						)
					)
					(dirS
						(if (== selectedButton BUTTON_RESTORE)
							(= selectedButton BUTTON_INTRO)
						else
							(++ selectedButton)
						)
					)
				)
				(event claimed: TRUE)
				(HighlightSelectedButton)
				(RedrawCast)
			)
			(keyDown
				(switch (event message?)
					(ENTER
						(ClickButton)
					)
					(SHIFTTAB
						(if (== selectedButton BUTTON_INTRO)
							(= selectedButton BUTTON_RESTORE)
						else
							(-- selectedButton)
						)
					)
					(TAB
						(if (== selectedButton BUTTON_RESTORE)
							(= selectedButton BUTTON_INTRO)
						else
							(++ selectedButton)
						)
					)
				)
				(event claimed: TRUE)
				(HighlightSelectedButton)
				(RedrawCast)
			)
		)
		(super handleEvent: event)
	)
)

(instance scorpS of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(scorp setMotion: MoveTo (Random 85 90) 160 self)
			)
			(1
				(scorp setCycle: 0)
				(= seconds (Random 2 3))
			)
			(2
				(scorp
					setCycle: Forward
					setMotion: MoveTo (Random 75 85) 90 self
				)
			)
			(3
				(scorp setCycle: 0)
				(= seconds (Random 2 3))
			)
			(4
				(scorp setCycle: Forward setMotion: MoveTo 80 0 self)
			)
			(5
				(scorp dispose: delete:)
			)
		)
	)
)
