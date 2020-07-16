;;; Sierra Script 1.0 - (do not remove this comment)
(script# WINDOW)
(include game.sh)
(use System)


(class SysWindow of Object
	(properties
		top 0
		left 0
		bottom 0
		right 0
		color 0
		back 15
		priority 15
		window 0
		type 0
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
	)
	
	(method (dispose)
		(if window (DisposeWindow window) (= window 0))
		(super dispose:)
	)
	
	(method (open)
		(= window
			(NewWindow
				top
				left
				bottom
				right
				title
				type
				priority
				color
				back
			)
		)
	)
)

(class Window of SysWindow
	(properties
		underBits 0
	)
	
	(method (doit)
	)
	
	(method (dispose)
		(self restore:)
		(if window (DisposeWindow window) (= window 0))
		(super dispose:)
	)
	
	(method (open)
		(= window
			(NewWindow
				top
				left
				bottom
				right
				title
				type
				priority
				color
				back
			)
		)
	)
	
	(method (handleEvent)
		(return FALSE)
	)
	
	(method (setMapSet &tmp mapSet)
		(= mapSet 0)
		(if (!= -1 color) (= mapSet (| mapSet VMAP)))
		(if (!= -1 priority) (= mapSet (| mapSet PMAP)))
		(return mapSet)
	)
	
	(method (move h v)
		(= left (+ left h))
		(= right (+ right v))
		(= right (+ right h))
		(= bottom (+ bottom v))
	)
	
	(method (moveTo h v)
		(self move: (- h left) (- v top))
	)
	
	(method (draw v p)
		(if (>= argc 1) (= color v))
		(if (>= argc 2) (= priority p))
		(Graph
			GFillRect
			top
			left
			bottom
			right
			(self setMapSet:)
			color
			priority
		)
	)
	
	(method (save)
		(= underBits
			(Graph
				GSaveBits
				top
				left
				bottom
				right
				(self setMapSet:)
			)
		)
	)
	
	(method (restore)
		(if underBits (Graph GRestoreBits underBits))
	)
	
	(method (inset h v)
		(= top (+ top v))
		(= left (+ left h))
		(= bottom (- bottom v))
		(= right (- right h))
	)
	
	(method (show)
		(Graph
			GShowBits
			top
			left
			bottom
			right
			(self setMapSet:)
		)
	)
	
	(method (erase)
		(self draw: back -1)
	)
	
	(method (center)
		(self
			moveTo:
				(/ (- (- brRight left) (- right left)) 2)
				(/ (- (- brBottom top) (- bottom top)) 2)
		)
	)
)
