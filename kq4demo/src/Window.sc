;;; Sierra Script 1.0 - (do not remove this comment)
(script# 981)
(include game.sh)
(use Save)


(class Window of SRDialog
	(properties
		left 0
		bottom 0
		right 0
		cycleDir 0
		color 0
		back 15
		priority -1
		window 0
		type $0000
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
		underBits 0
	)
	
	(method (doit)
	)
	
	(method (dispose)
		(self restore:)
		(if window (DisposeWindow window))
		(super dispose:)
	)
	
	(method (center)
		(= window
			(NewWindow
				left
				bottom
				right
				cycleDir
				title
				type
				priority
				color
				back
			)
		)
	)
	
	(method (handleEvent)
		(return 0)
	)
	
	(method (erase &tmp temp0)
		(= temp0 0)
		(if (!= -1 color) (= temp0 (| temp0 $0001)))
		(if (!= -1 priority) (= temp0 (| temp0 $0002)))
		(return temp0)
	)
	
	(method (move param1 param2)
		(= bottom (+ bottom param1))
		(= cycleDir (+ cycleDir param2))
		(= cycleDir (+ cycleDir param1))
		(= right (+ right param2))
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 bottom) (- param2 left))
	)
	
	(method (draw theColor thePriority)
		(if (>= argc 1) (= color theColor))
		(if (>= argc 2) (= priority thePriority))
		(kernel_112 ;EO: This kernel function is Graph, which is not in this game's VOCAB.999
			11
			left
			bottom
			right
			cycleDir
			(self erase:)
			color
			priority
		)
	)
	
	(method (save)
		(= underBits
			(kernel_112 7 left bottom right cycleDir (self erase:))
		)
	)
	
	(method (restore)
		(if underBits (kernel_112 8 underBits))
	)
	
	(method (ux param1 param2)
		(= left (+ left param2))
		(= bottom (+ bottom param1))
		(= right (- right param2))
		(= cycleDir (- cycleDir param1))
	)
	
	(method (addToPic)
		(kernel_112 12 left bottom right cycleDir (self erase:))
	)
	
	(method (uy)
		(self draw: back -1)
	)
	
	(method (top)
		(self
			moveTo:
				(/ (- (- brRight bottom) (- cycleDir bottom)) 2)
				(/ (- (- brBottom left) (- right left)) 2)
		)
	)
)
