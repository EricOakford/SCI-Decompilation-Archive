;;; Sierra Script 1.0 - (do not remove this comment)
(script# 993)
(include game.sh)
(use System)


(class File of Object
	(properties
		handle 0
		name "gamefile.sh"
	)
	
	(method (dispose)
		(self close:)
		(super dispose:)
	)
	
	(method (showStr param1)
		(Format param1 993 0 name)
	)
	
	(method (open param1)
		(= handle
			(switch argc
				(0 (FOpen name 0))
				(1 (FOpen name param1))
				(else  -1)
			)
		)
		(return (if (== handle -1) 0 else self))
	)
	
	(method (write param1 &tmp temp0)
		(if (not handle) (self open:))
		(= temp0 0)
		(while (< temp0 argc)
			(FPuts handle [param1 temp0])
			(++ temp0)
		)
	)
	
	(method (read param1 param2)
		(if (!= argc 2) (return 0))
		(if (not handle) (self open: 1))
		(return (FGets param1 param2 handle))
	)
	
	(method (close)
		(if handle (FClose handle) (= handle 0))
	)
)
