;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FLAGS.SC
;;;;
;;;;	(c) Sierra On-Line, Inc., 1992
;;;;
;;;;	Author:	Brian K. Hughes
;;;;	Updated:	
;;;;
;;;;	Classes:
;;;;		Flag


(script# FLAGS)
(include game.sh)
(use System)

(class Flags kindof Object
	(properties
		size		0		; number of bits desired (will be bumped to even mult of 16)
		array		0		; <private> pointer to bit array
	)
;;;	(methods
;;;		setSize
;;;		set
;;;		clear
;;;		test
;;;	)

	(method (init)
		(if (and size (not array))
			(self setSize: size)
		)
	)
	(method (setSize howMany &tmp numWords i)
		(= numWords (/ howMany 16))
		(if (mod howMany 16)
			(++ numWords)
		)
		(= size (* numWords 16))

		; Allocate & clear the flags array
		(= array (Memory MNeedPtr (* numWords 2)))

		(for 	((= i 0))
				(< i numWords)
				((++ i))
			(Memory MWriteWord (+ array (* i 2)) 0)
;			(= [array i] 0)
		)
	)
	(method (dispose)
		(if array
			(Memory MDisposePtr array)
			(= array 0)
		)
		(super dispose:)
	)
	(method (set flags &tmp theWord offset)
		(while argc
			(= offset (* (/ [flags (-- argc)] 16) 2))
			(= theWord (Memory MReadWord (+ array offset)))
			(|= theWord (>> $8000 (mod [flags argc] 16)))
			(Memory MWriteWord (+ array offset) theWord)
		)
	)
	(method (clear flags &tmp theWord offset)
		(while argc
			(= offset (* (/ [flags (-- argc)] 16) 2))
			(= theWord (Memory MReadWord (+ array offset)))
			(&= theWord (~ (>> $8000 (mod [flags argc] 16))))
			(Memory MWriteWord (+ array offset) theWord)
		)
	)
	(method (test theFlag &tmp theWord offset)
		(= offset (* (/ theFlag 16) 2))
		(= theWord (Memory MReadWord (+ array offset)))
		(return (& theWord (>> $8000 (mod theFlag 16))))
	)
)
