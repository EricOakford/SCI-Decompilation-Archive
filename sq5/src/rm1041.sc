;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1041)
(include game.sh) (include "1041.shm") (include "0.shm")
(use Main)
(use Talker)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1041 0
	beaTalker 10
	drooleTalker 12
	floTalker 13
	rogTalker 15
)

(local
	creditCase =  1
	local1 =  13
	local2
	[str 500]
)
(instance rm1041 of Room
	(properties
		picture 132
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 715 716)
		(LoadMany RES_PIC 136 1)
		(lights1 init:)
		(bea init:)
		(theMusic2 number: 654 setLoop: -1 play:)
		(super init:)
		(curRoom setScript: sFinale)
	)
)

(instance sFinale of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1 (messager say: N_FINALE NULL NULL 0 self))
			(2 (bea setCycle: EndLoop self))
			(3
				(theMusic2 number: 709 setLoop: 1 play:)
				(= seconds 1)
			)
			(4 (bea setCycle: BegLoop self))
			(5
				(theMusic2 number: 163 setLoop: 1 play:)
				(bea dispose:)
				(lights1 dispose:)
				(lights2 dispose:)
				(lights3 dispose:)
				(lights4 dispose:)
				(lights5 dispose:)
				(lights6 dispose:)
				(= cycles 1)
			)
			(6
				(DrawPic 136 PLAIN)
				(= seconds 3)
			)
			(7
				(PalVary PALVARYKILL)
				(PalVary PALVARYSTART 2100 1)
				(= seconds 5)
			)
			(8
				(= next sClosing)
				(self dispose:)
			)
		)
	)
)

(instance sClosing of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(keyDownHandler addToFront: self)
				(mouseDownHandler addToFront: self)
				(theMusic2 fade:)
				(PalVary PALVARYKILL)
				(DrawPic 1 BLACKOUT)
				(proc0_11)
				(= seconds 3)
			)
			(1
				(Message MsgGet 1041 N_CLOSING NULL NULL 1 @str)
				(Display @str
					p_at 40 65
					p_mode teJustCenter
					p_color 207
					p_font 1307
					p_width 240
				)
			)
			(2
				(= seconds 0)
				(DrawPic 1 WIPELEFT)
				(= seconds 2)
			)
			(3
				(Message MsgGet 1041 N_CLOSING NULL NULL 2 @str)
				(Display @str
					p_at 40 65
					p_mode teJustCenter
					p_color 207
					p_font 1307
					p_width 240
				)
			)
			(4
				(= seconds 0)
				(DrawPic 1 WIPELEFT)
				(= seconds 2)
			)
			(5
				(Palette PALIntensity 231 232 0)
				(= ticks 5)
			)
			(6
				(Message MsgGet 1041 N_CREDITS NULL NULL creditCase @str)
				(Display @str
					p_at 40 65
					p_mode teJustCenter
					p_color 231
					p_font 1307
					p_width 240
				)
				(= cycles 1)
			)
			(7
				(Palette PALIntensity 231 232 local2)
				(if (< (= local2 (+ local2 10)) 101) (-- state))
				(= ticks 5)
			)
			(8
				(if (== creditCase 12) (sprintLogo init:))
				(= seconds 4)
			)
			(9
				(if (== creditCase 12) (sprintLogo dispose:))
				(Palette PALIntensity 231 232 local2)
				(if (> (= local2 (- local2 10)) 0) (-- state))
				(= ticks 5)
			)
			(10
				(Display @str
					p_at 40 65
					p_mode teJustCenter
					p_color 0
					p_font 1307
					p_width 240
				)
				(if (< (++ creditCase) local1) (= state (- state 6)))
				(= seconds 2)
			)
			(11
				(messager say: N_QUIT V_LOOK NULL 4 self 0)
			)
			(12
				(keyDownHandler delete: self)
				(mouseDownHandler delete: self)
				(= quit TRUE)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(or (== state 1) (== state 3))
				(& (event type?) (| userEvent mouseDown keyDown))
			)
			(self cue:)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
)

(instance bea of Prop
	(properties
		x 92
		y 39
		view 715
	)
)

(instance lights1 of Prop
	(properties
		x 165
		y 60
		view 715
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward)
		(lights2 init: setCycle: Forward)
		(lights3 init: setCycle: Forward)
		(lights4 init: setCycle: Forward)
		(lights5 init: setCycle: Forward)
		(lights6 init: setCycle: Forward)
	)
)

(instance lights2 of Prop
	(properties
		x 227
		y 20
		view 715
		loop 7
	)
)

(instance lights3 of Prop
	(properties
		x 231
		y 31
		view 715
		loop 5
	)
)

(instance lights4 of Prop
	(properties
		x 232
		y 43
		view 715
		loop 3
	)
)

(instance lights5 of Prop
	(properties
		x 232
		y 45
		view 715
		loop 4
		cel 3
	)
)

(instance lights6 of Prop
	(properties
		x 315
		y 71
		view 715
		loop 6
	)
)

(instance floEyes of Actor
	(properties
		x 304
		y 89
		view 716
	)
)

(instance drooleMouth of Actor
	(properties
		x 11
		y 10
		view 716
		loop 1
	)
)

(instance rogMouth of Actor
	(properties
		nsTop 30
		nsLeft 12
		view 715
		loop 1
	)
)

(instance beaMouth of Actor
	(properties
		nsTop 17
		nsLeft 31
		view 715
		loop 2
	)
)

(instance beaTalker of Talker
	(properties
		x 80
		y 14
		view 715
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 160
			tailY: 37
			xOffset: 60
			isBottom: 0
		)
		(super init: 0 0 beaMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance rogTalker of Talker
	(properties
		x 80
		y 14
		view 715
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 160
			tailY: 38
			xOffset: 60
			isBottom: 1
		)
		(super init: 0 0 rogMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance drooleTalker of Talker
	(properties
		x 18
		y 108
		view 715
		loop 9
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 115
			tailY: 122
			xOffset: 40
			isBottom: 1
		)
		(super init: 0 0 drooleMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance floTalker of Narrator
	(properties)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 210
			tailY: 100
			xOffset: -50
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance sprintLogo of View
	(properties
		x 50
		y 80
		view 925
	)
)
