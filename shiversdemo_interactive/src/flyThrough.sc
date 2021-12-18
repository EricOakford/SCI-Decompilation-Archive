;;; Sierra Script 1.0 - (do not remove this comment)
(script# 937)
(include sci.sh)
(use Main)
(use Procs)

(public
	flyThrough 0
)

(instance flyThrough of ShiversRoom
	(properties)
	
	(method (init &tmp [temp0 4])
		(super init: &rest)
		(if (> (theGame detailLevel:) 5)
			(proc951_4 35)
			(proc951_4 38)
		else
			(proc951_3 35)
			(proc951_3 38)
		)
		(self playVMD: 900 self)
		(curRoom newRoom: 910)
	)
)
