;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh) (include "100.shm")
(use Main)
(use Timer)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(instance rm100 of Room
	
	(method (init)
		(super init: &rest)
		(if (Btst fSawIntro)
			(timeKeeper setReal: timeKeeper 30)
			(button1 init:)
			(button2 init:)
			(button3 init:)
			(self drawPic: 120 SHOW_FADE_IN TRUE)
		else
			(= global122 0)
			(self setScript: intro self)
		)
	)
	
	(method (cue)
		(theGame handsOn:)
		(super cue: &rest)
	)
)

(instance timeKeeper of Timer

	(method (cue)
		(cond 
			((== global122 0)
				(if (not (curRoom script?))
					(= global122 1)
					(curRoom setScript: to300)
				)
			)
			((not (curRoom script?))
				(= global122 0)
				(curRoom setScript: to200)
			)
		)
	)
)

(instance intro of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(if isWindows
					(ShowMovie 1 0 {moon3.avi})
					(ShowMovie 1 1 0 30)
					(Palette PalLoad 380 2)
					(ShowMovie 1 7)
					(ShowMovie 1 2)
					(ShowMovie 1 6)
				)
				(= cycles 1)
			)
			(2
				(if isWindows
					(ShowMovie 1 0 {wolf3.avi})
					(ShowMovie 1 1 60 35)
					(Palette PalLoad 390 2)
					(ShowMovie 1 7)
					(ShowMovie 1 2)
					(ShowMovie 1 6)
				)
				(= cycles 1)
			)
			(3
				(proc0_5 1 3 self)
			)
			(4
				(timeKeeper setReal: timeKeeper 30)
				(button1 init:)
				(button2 init:)
				(button3 init:)
				(curRoom drawPic: 120 SHOW_BLACKOUT)
				(proc0_5 0 3)
				(= cycles 1)
			)
			(5
				(Bset fSawIntro)
				(self dispose:)
			)
		)
	)
)

(class ButtonView of View
	
	(method (handleEvent event &tmp ret)
		(cond 
			((event claimed?) (return TRUE))
			(
				(and
					(& (event type?) userEvent)
					(self onMe: event)
					(self isNotHidden:)
				)
				(self cel: 1)
				(self action:)
				(= ret (event claimed: TRUE))
			)
		)
		(return ret)
	)
	
	(method (action)
	)
)

(instance to300 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc0_5 1 3 self)
			)
			(1
				(curRoom drawPic: 1111)
				(button1 hide: dispose:)
				(button2 hide: dispose:)
				(button3 hide: dispose:)
				(= cycles 1)
			)
			(2
				(timeKeeper dispose:)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance to200 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc0_5 1 3 self)
			)
			(1
				(button1 hide: dispose:)
				(button2 hide: dispose:)
				(button3 hide: dispose:)
				(= cycles 1)
			)
			(2
				(timeKeeper dispose:)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance toQuit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc0_5 1 3 self)
			)
			(1 (theGame quitGame:))
		)
	)
)

(instance button1 of ButtonView
	(properties
		x 50
		y 150
		view 100
	)
	
	(method (action)
		(curRoom setScript: to300)
	)
)

(instance button2 of ButtonView
	(properties
		x 132
		y 150
		view 100
		loop 1
	)
	
	(method (action)
		(= global122 1)
		(curRoom setScript: to200)
	)
)

(instance button3 of ButtonView
	(properties
		x 216
		y 150
		view 100
		loop 2
	)
	
	(method (action)
		(curRoom setScript: toQuit)
	)
)
