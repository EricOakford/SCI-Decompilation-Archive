;;; Sierra Script 1.0 - (do not remove this comment)
(script# STATUSBAR) ;205
(include game.sh)
(use Main)
(use Actor)
(use System)


(class StatusBar of Object
	(properties
		value 0
		max 1000
		x 1000
		y 1000
		priority 15
		titleCel 0
		lineObj 0
	)
	
	(method (init)
		(Load VIEW statusBarView)
		((PicView new:)
			view: statusBarView
			loop: 0
			cel: titleCel
			x: x
			y: y
			z: 1
			priority: priority
			init:
		)
		;HP, SP, MP, Time
		((PicView new:)
			view: statusBarView
			loop: 0
			cel: 3
			x: x
			y: y
			z: 2
			priority: priority
			init:
		)
		;Border around value.
		(addToPics doit:)
		((= lineObj (Prop new:))
			ignoreActors:
			view: statusBarView
			posn: x y 3
			init:
		)
		;actual percentage bar
		(self draw:)
	)
	
	(method (dispose)
		(if (IsObject lineObj)
			(lineObj dispose:
			))
		(super dispose:)
	)
	
	(method (draw &tmp valCel scaledValue)
		(if (< max 1)
			(= max 1)
		)
		(if (> (= scaledValue value) max)
			(= scaledValue max)
		)
		;convert scaledValue to percentage of max
		(= scaledValue (/ (+ (* scaledValue 100) max -1) max))
		;then divide by 10 to get the cell # to show.
		(= valCel (/ (+ scaledValue 9) 10))
		(lineObj
			loop: 1
			cel: valCel
			posn: (+ x 2) y
			setPri: priority
			stopUpd:
		)
	)
)
