;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWATRESTORE)
(include game.sh)
(use DButton)
(use DIcon)
(use Save)
(use System)


(class SwatRestoreDlg of SRDialog
	
	(method (doit &tmp temp0)
		(= temp0 (super doit: &rest))
		(DisposeScript SWATRESTORE temp0)
	)
	
	(method (dispose)
		(restoreView dispose:)
		(super dispose: &rest)
	)
	
	(method (initStrs)
	)
	
	(method (initItems &tmp temp0)
		(selectorObj width: 118 length: 11 setSize:)
		(self moveItems: 2)
		(self moveItems: 3)
		(self add: restoreView)
		(super initItems:)
	)
	
	(method (setupObjs)
		(super setupObjs:)
		(= editObj (= textObj (= changeDirObj 0)))
		(selectorObj upButton: selectUp downButton: selectDown)
		(okObj view: 300 loop: 1)
		(deleteObj view: 300 loop: 2)
		(cancelObj view: 300 loop: 3)
	)
	
	(method (setupOkButn)
		(okObj loop: 1)
	)
	
	(method (getDefaultItem param1 param2)
		(return (if param2 okObj else cancelObj))
	)
	
	(method (displayReplaceMsg)
		(return 1)
	)
	
	(method (displayDeleteMsg)
		(return 1)
	)
	
	(method (moveItems param1)
		(if (and selectorObj (OneOf param1 0 2))
			(selectorObj moveTo: 18 47)
		)
		(if (and okObj (OneOf param1 0 3))
			(okObj moveTo: 158 (selectorObj nsTop?))
		)
		(if (and deleteObj (OneOf param1 0 5))
			(deleteObj
				moveTo: 158 (+ (okObj nsBottom?) buttonMargin)
			)
		)
		(if (and cancelObj (OneOf param1 0 4))
			(cancelObj
				moveTo:
					158
					(if changeDirObj
						(+ (changeDirObj nsBottom?) buttonMargin)
					else
						(+ (deleteObj nsBottom?) buttonMargin)
					)
			)
		)
	)
	
	(method (getStatus)
		(return 0)
	)
)

(instance restoreView of DIcon
	(properties
		view 300
	)
)

(instance selectUp of DButton
	(properties
		x 141
		y 46
		view 300
		loop 4
	)
	
	(method (moveTo)
		(super moveTo: 141 46)
	)
)

(instance selectDown of DButton
	(properties
		x 141
		y 110
		view 300
		loop 5
	)
	
	(method (moveTo)
		(super moveTo: 141 110)
	)
)
