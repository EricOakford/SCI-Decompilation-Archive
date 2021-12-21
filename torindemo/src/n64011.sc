;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64011)
(include sci.sh)
(use Main)
(use Print)

(public
	proc64011_0 0
)

(procedure (proc64011_0 param1 &tmp temp0)
	(switch param1
		(1 (= temp0 (localproc_00ac)))
		(2 (= temp0 (localproc_0229)))
		(3 (= temp0 (localproc_0355)))
		(4 (= temp0 (localproc_0430)))
		(5 (= temp0 (localproc_05ab)))
		(else  (= temp0 8000))
	)
	(DisposeScript -1525 temp0)
)

(procedure (localproc_00ac &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: LOOKUP_ERROR
			addButton: 10000 LOOKUP_ERROR 0 31
			addButton: 11100 LOOKUP_ERROR 0 62
			addButton: 11000 {City} 0 93
			addButton: 10100 LOOKUP_ERROR 0 124
			addButton: 16000 LOOKUP_ERROR 0 156
			addButton: 16100 LOOKUP_ERROR 0 187
			addButton: 11200 LOOKUP_ERROR 140 31
			addButton: 11300 {AirDuctEnd} 140 62
			addButton: 12000 LOOKUP_ERROR 140 93
			addButton: 14000 {Backstage} 140 124
			addButton: 13000 LOOKUP_ERROR 200 93
			addButton: 15000 LOOKUP_ERROR 240 93
			addButton: 15100 LOOKUP_ERROR 240 62
			addButton: 15200 LOOKUP_ERROR 240 31
			init:
		)
	)
)

(procedure (localproc_0229 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: LOOKUP_ERROR
			addButton: 20000 LOOKUP_ERROR 0 31
			addButton: 20100 LOOKUP_ERROR 160 31
			addButton: 20200 LOOKUP_ERROR 80 31
			addButton: 20300 LOOKUP_ERROR 240 31
			addButton: 20400 LOOKUP_ERROR 80 62
			addButton: 20500 LOOKUP_ERROR 240 62
			addButton: 20900 LOOKUP_ERROR 240 93
			addButton: 20600 LOOKUP_ERROR 240 121
			addButton: 20700 LOOKUP_ERROR 80 93
			addButton: 20800 LOOKUP_ERROR 0 93
			init:
		)
	)
)

(procedure (localproc_0355 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: LOOKUP_ERROR
			addButton: 30000 LOOKUP_ERROR 0 31
			addButton: 30100 LOOKUP_ERROR 80 31
			addButton: 30200 LOOKUP_ERROR 80 62
			addButton: 30300 LOOKUP_ERROR 160 62
			addButton: 30400 LOOKUP_ERROR 240 62
			addButton: 30500 LOOKUP_ERROR 240 31
			init:
		)
	)
)

(procedure (localproc_0430 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: LOOKUP_ERROR
			addButton: -25536 LOOKUP_ERROR 0 31
			addButton: -24636 LOOKUP_ERROR 80 31
			addButton: -22536 LOOKUP_ERROR 160 31
			addButton: -24736 LOOKUP_ERROR 0 62
			addButton: -24836 LOOKUP_ERROR 80 62
			addButton: -24936 LOOKUP_ERROR 160 62
			addButton: -25036 LOOKUP_ERROR 240 62
			addButton: -25436 LOOKUP_ERROR 0 93
			addButton: -25136 LOOKUP_ERROR 80 93
			addButton: -25236 LOOKUP_ERROR 160 93
			addButton: -25336 LOOKUP_ERROR 0 124
			addButton: -23336 LOOKUP_ERROR 80 124
			addButton: -23436 LOOKUP_ERROR 160 124
			addButton: -23536 LOOKUP_ERROR 240 124
			init:
		)
	)
)

(procedure (localproc_05ab &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: LOOKUP_ERROR
			addButton: -15536 LOOKUP_ERROR 0 31
			addButton: -15436 LOOKUP_ERROR 240 31
			addButton: -15336 LOOKUP_ERROR 160 31
			addButton: -15236 LOOKUP_ERROR 80 31
			addButton: -15136 LOOKUP_ERROR 80 62
			addButton: -15036 LOOKUP_ERROR 160 62
			addButton: -14936 LOOKUP_ERROR 80 93
			addButton: -14836 LOOKUP_ERROR 80 124
			addButton: -14736 LOOKUP_ERROR 160 124
			addButton: -14636 LOOKUP_ERROR 240 124
			addButton: -12536 LOOKUP_ERROR 80 155
			addButton: -12436 LOOKUP_ERROR 160 155
			addButton: -12336 LOOKUP_ERROR 240 155
			addButton: -14536 LOOKUP_ERROR 240 186
			addButton: -10536 LOOKUP_ERROR 160 186
			addButton: -14436 LOOKUP_ERROR 80 186
			addButton: -14336 LOOKUP_ERROR 80 217
			addButton: -14236 LOOKUP_ERROR 160 217
			addButton: -14136 LOOKUP_ERROR 240 217
			init:
		)
	)
)
