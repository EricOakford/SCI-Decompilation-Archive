;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use System)

(public
	addCluesCode 0
	dropCluesCode 1
)

(local
	local0
	local1
)
(instance addCluesCode of Code

	(method (doit param1 &tmp temp0)
		(switch (/ param1 256)
			(1
				(= local1 18)
				(= local0 @global202)
			)
			(2
				(= local1 8)
				(= local0 @global220)
			)
			(3
				(= local1 35)
				(= local0 @global228)
			)
			(4
				(= local1 32)
				(= local0 @global263)
			)
		)
		(= temp0 0)
		(while (< temp0 local1)
			(if
				(==
					(mod (Memory MReadWord (+ local0 (* 2 temp0))) 4096)
					param1
				)
				(= temp0 local1)
			)
			(if (not (Memory MReadWord (+ local0 (* 2 temp0))))
				(Memory MWriteWord (+ local0 (* 2 temp0)) param1)
				(= temp0 local1)
			)
			(++ temp0)
		)
	)
)

(instance dropCluesCode of Code
	
	(method (doit param1 &tmp temp0 temp1)
		(switch (/ param1 256)
			(1
				(= local1 18)
				(= local0 @global202)
			)
			(2
				(= local1 8)
				(= local0 @global220)
			)
			(3
				(= local1 35)
				(= local0 @global228)
			)
			(4
				(= local1 32)
				(= local0 @global263)
			)
		)
		(= temp0 0)
		(while (< temp0 local1)
			(if
				(==
					(mod (Memory MReadWord (+ local0 (* 2 temp0))) 4096)
					param1
				)
				(= temp1 temp0)
				(= temp0 local1)
			)
			(++ temp0)
		)
		(= temp0 temp1)
		(while (< temp0 local1)
			(Memory MWriteWord
				(+ local0 (* 2 temp0))
				(Memory MReadWord (+ local0 (* 2 temp0) 2))
			)
			(++ temp0)
		)
		(Memory MWriteWord (+ local0 (* 2 (- local1 1))) 0)
	)
)
