;;; Sierra Script 1.0 - (do not remove this comment)
(script# 892)
(include game.sh)
(use Main)
(use Window)
(use Actor)


(class BalloonWindow of SysWindow
	(properties
		balloonView 0
		balloonTail 0
		x 0
		y 0
		height 0
		width 0
		tailCel 0
		tailOffset 0
		tailScale 0
	)
	
	(method (dispose)
		(if balloonView
			(balloonView dispose:)
			(= balloonView 0)
		)
		(if balloonTail
			(balloonTail dispose:)
			(= balloonTail 0)
		)
		(super dispose:)
	)
	
	(method (open &tmp savePort theX theY)
		(= priority 15)
		(= color 0)
		(= back 7)
		(= top (+ y 14))
		(= left (+ x 23))
		(= brTop top)
		(= brLeft left)
		(= brBottom bottom)
		(= brRight right)
		(= theX (/ (* 128 (+ width 25)) 175))
		(= theY (/ (* 128 (+ height 15)) 60))
		(Animate (cast elements?) FALSE)
		((= balloonTail (View new:))
			view: 914
			name: {balloonTail}
			setPri: 14
			loop: 0
			cel: tailCel
			x: (+ ((ScriptID 895 0) x?) tailOffset)
			y: (- ((ScriptID 895 0) nsTop?) 5)
			setScale:
			scaleX: tailScale
			scaleY: tailScale
			init:
			stopUpd:
		)
		((= balloonView (View new:))
			view: 915
			name: {balloonView}
			setPri: 15
			loop: 0
			cel: 0
			x: x
			y: y
			setScale:
			scaleX: theX
			scaleY: theY
			init:
			stopUpd:
		)
		(Animate (cast elements?) 0)
		(= type 128)
		(super open: &rest)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GShowBits top left bottom right VMAP)
		(SetPort savePort)
	)
)
