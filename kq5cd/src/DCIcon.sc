;;; Sierra Script 1.0 - (do not remove this comment)
(script# 967)
(include sci.sh)
(use Intrface)
(use Motion)


(class DCIcon of DIcon
	(properties
		type $0004
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		view 0
		loop 0
		cel 0
		cycler 0
		cycleSpeed 16
		signal $0000
		count -1
		talker 0
		loops 0
	)
	
	(method (init)
		(cond 
			((== cycler -1) (= cycler 0))
			(cycler ((= cycler (cycler new:)) init: self))
			(else ((= cycler (Fwd new:)) init: self))
		)
		(= loops 0)
	)
	
	(method (dispose)
		(if cycler (cycler dispose:))
		(super dispose:)
	)
	
	(method (cycle &tmp theCel)
		(if cycler
			(= theCel cel)
			(cycler doit:)
			(if (!= cel theCel)
				(if (and (!= count -1) (> loops count))
					(if talker
						(= loop (= cel 0))
					else
						(= cel (self lastCel:))
					)
				)
				(self draw:)
				(if
					(and
						(!= count -1)
						(== cel (self lastCel:))
						(<= loops count)
					)
					(++ loops)
					(if
						(and
							talker
							(> (NumLoops self) 1)
							(or loop (< (Random 1 100) 51))
						)
						(= loop (^ loop $0001))
					)
				)
			)
		)
	)
	
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
	
	(method (setCel)
	)
	
	(method (startUpd)
	)
)
